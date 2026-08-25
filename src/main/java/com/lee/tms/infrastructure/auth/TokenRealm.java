package com.lee.tms.infrastructure.auth;

import com.lee.tms.infrastructure.rest.RestCode;
import com.lee.tms.infrastructure.rest.RestException;
import com.lee.tms.modules.system.entity.Role;
import com.lee.tms.modules.system.entity.Token;
import com.lee.tms.modules.system.entity.User;
import com.lee.tms.modules.system.service.PermissionService;
import com.lee.tms.modules.system.service.TokenService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component ("authorizer")
public class TokenRealm extends AuthorizingRealm
{
    @Lazy
    @Autowired
    private TokenService tokenService;

    @Lazy
    @Autowired
    private PermissionService permissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        AuthUser user = (AuthUser) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(user.getRoleKeys());
        info.setStringPermissions(user.getPermKeys());
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
    {
        String tokenStr = (String) token.getPrincipal();
        Token tokenEntity = tokenService.getTokenWithUser(tokenStr);
        if (tokenEntity == null || tokenEntity
                .getExpireDate()
                .isBefore(LocalDateTime.now()))
        {
            throw new RestException(RestCode.INVALID_TOKEN);
        }
        User user = tokenEntity.getUser();
        if (user == null)
        {
            throw new RestException(RestCode.SYS_ERR, "用户不存在 [ID = {}] [Token = {}]", tokenEntity.getUserId(), tokenStr);
        }
        AuthUser authUser = AuthUser.fromModel(user);
        authUser.setRoleKeys(permissionService.getUserRoleKeys(user.getId()));
        authUser.setPermKeys(permissionService.getUserFunctionKeys(user.getId()));
        authUser.setRoles(tokenEntity.getRoles());
        authUser.setMinRoleLevel(tokenEntity
                                         .getRoles()
                                         .stream()
                                         .map(Role::getRoleLevel)
                                         .min(Long::compare)
                                         .orElse(Long.MAX_VALUE));
        return new SimpleAuthenticationInfo(authUser, tokenStr, getName());
    }

    @Override
    public boolean isPermitted(PrincipalCollection principals, String permission)
    {
        AuthUser authUser = (AuthUser) principals.getPrimaryPrincipal();
        return authUser.isRoot() || super.isPermitted(principals, permission);
    }

    @Override
    public boolean hasRole(PrincipalCollection principals, String roleIdentifier)
    {
        AuthUser authUser = (AuthUser) principals.getPrimaryPrincipal();
        return authUser.isRoot() || super.hasRole(principals, roleIdentifier);
    }

    @Override
    public boolean supports(AuthenticationToken token)
    {
        return token instanceof AuthToken;
    }
}
