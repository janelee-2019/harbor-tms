package com.lee.tms.infrastructure.auth;

import cn.hutool.core.util.ObjectUtil;
import com.lee.tms.infrastructure.rest.RestException;
import com.lee.tms.modules.system.entity.User;
import com.lee.tms.modules.system.service.UserService;
import jakarta.annotation.PostConstruct;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public final class AuthContext
{
    private static AuthContext authContext;
    @Autowired
    UserService userService;

    public static AuthUser getAuthUser()
    {
        AuthUser user = (AuthUser) SecurityUtils
                .getSubject()
                .getPrincipal();
        if (user == null)
        {
            // throw new RestException("无法获取当前用户");
        }
        return user;
    }

    public static Long getCurrentUserId()
    {
        AuthUser user = AuthContext.getAuthUser();
        if (ObjectUtil.isNull(user))
        {
            throw new RestException("当前用户未注册");
        }
        return user.getId();
    }

    public static String getUserName(Long id)
    {
        if (id == null)
            return null;
        List<User> users = authContext.userService.getAllUsers();
        return users
                .stream()
                .filter(user -> id.equals(user.getId()))
                .map(User::getEmployeeName)
                .findFirst()
                .orElse("");
    }

    public static boolean hasRole(String roleKey)
    {
        return SecurityUtils
                .getSubject()
                .hasRole(roleKey);
    }

    public static boolean hasAllRole(Collection<String> roleKeys)
    {
        return SecurityUtils
                .getSubject()
                .hasAllRoles(roleKeys);
    }

    public static boolean isRoot(Long userId)
    {
        return userId != null && userId == 0;
    }

    @PostConstruct
    public void init()
    {
        authContext = this;
        authContext.userService = this.userService;
    }
}
