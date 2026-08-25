package com.lee.tms.modules.system.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ICaptcha;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lee.tms.config.props.AuthProps;
import com.lee.tms.infrastructure.auth.AuthContext;
import com.lee.tms.infrastructure.auth.AuthUser;
import com.lee.tms.infrastructure.rest.RestCode;
import com.lee.tms.infrastructure.rest.RestException;
import com.lee.tms.infrastructure.util.IpUtil;
import com.lee.tms.infrastructure.util.ServletUtil;
import com.lee.tms.modules.system.dto.body.AuthLoginBody;
import com.lee.tms.modules.system.dto.vo.AuthLoginVo;
import com.lee.tms.modules.system.dto.vo.AuthUserInfoVo;
import com.lee.tms.modules.system.dto.vo.FunctionMenuVo;
import com.lee.tms.modules.system.entity.Captcha;
import com.lee.tms.modules.system.entity.Token;
import com.lee.tms.modules.system.entity.User;
import com.lee.tms.modules.system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService
{
    @Autowired
    private AuthProps authProps;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public ICaptcha createCaptcha(String uuid)
    {
        if (captchaService.count(Wrappers.<Captcha>lambdaQuery().eq(Captcha::getUuid, uuid)) > 0)
        {
            throw new RestException(RestCode.SYS_ERR, "验证码 UUID 重复 [UUID = {}]", uuid);
        }

        ICaptcha captchaImage = CaptchaUtil.createLineCaptcha(authProps.getCaptchaWidth(), authProps.getCaptchaHeight());
        Captcha captcha = new Captcha();
        captcha.setUuid(uuid);
        captcha.setCode(captchaImage.getCode());
        LocalDateTime now = LocalDateTimeUtil.now();
        captcha.setExpireDate(LocalDateTimeUtil.offset(now, authProps.getCaptchaExpiresInMinutes(), ChronoUnit.MINUTES));
        captcha.setCreateDate(now);
        captcha.insert();
        return captchaImage;
    }

    @Override
    public void validateCaptcha(String uuid, String code)
    {
        Captcha captcha = captchaService.getOne(Wrappers.<Captcha>lambdaQuery().eq(Captcha::getUuid, uuid));
        if (captcha == null)
        {
            throw new RestException(RestCode.INVALID_CAPTCHA);
        }
        if (!StrUtil.equalsIgnoreCase(captcha.getCode(), code))
        {
            throw new RestException(RestCode.INVALID_CAPTCHA);
        }
        captcha.deleteById();
    }

    @Transactional (rollbackFor = Exception.class)
    @Override
    public AuthLoginVo login(AuthLoginBody body)
    {
        // 校验用户名和密码
        User user = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getLoginName, body.getLoginName()));
        if (user == null)
        {
            throw new RestException(RestCode.INVALID_LOGIN_CREDENTIALS);
        }
        String inputPass = DigestUtil.md5Hex(body.getLoginPassword() + user.getSalt());
        String existPass = user.getLoginPassword();
        if (!StrUtil.equals(inputPass, existPass))
        {
            throw new RestException(RestCode.INVALID_LOGIN_CREDENTIALS);
        }

        // 生成用户令牌
        // 先删除已存在令牌
        tokenService.remove(Wrappers.<Token>lambdaUpdate().eq(Token::getUserId, user.getId()));
        String tokenStr = IdUtil.fastSimpleUUID();
        Token token = new Token();
        token.setToken(tokenStr);
        token.setUserId(user.getId());
        LocalDateTime now = LocalDateTimeUtil.now();
        token.setExpireDate(LocalDateTimeUtil.offset(now, authProps.getTokenExpiresInDays(), ChronoUnit.DAYS));
        token.setCreateDate(now);
        token.insert();

        // 更新用户登录记录
        user.setLastLoginDate(now);
        user.setLastLoginIp(IpUtil.getIpAddr(ServletUtil.getRequest()));
        user.updateById();

        // 组装 vo
        AuthLoginVo vo = AuthLoginVo.fromModel(user);
        vo.setToken(tokenStr);
        vo.setRoles(permissionService.getUserRoleKeys(user.getId()));
        vo.setPerms(permissionService.getUserFunctionKeys(user.getId()));
        vo.setMenus(permissionService.getUserFunctionMenus(user.getId()));
        return vo;
    }

    @Override
    public void logout()
    {
        AuthUser authUser = AuthContext.getAuthUser();
        tokenService.remove(Wrappers.<Token>lambdaUpdate().eq(Token::getUserId, authUser.getId()));
    }

    @Override
    public List<FunctionMenuVo> getCurrentUserMenus()
    {
        AuthUser authUser = AuthContext.getAuthUser();
        return permissionService.getUserFunctionMenus(authUser.getId());
    }

    public AuthUserInfoVo getCurrentUserAuthInfo()
    {
        AuthUser authUser = AuthContext.getAuthUser();
        AuthUserInfoVo vo = AuthUserInfoVo.fromAuthUser(authUser);
        vo.setMenus(permissionService.getUserFunctionMenus(authUser.getId()));
        return vo;
    }
}
