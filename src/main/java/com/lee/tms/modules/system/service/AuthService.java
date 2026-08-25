package com.lee.tms.modules.system.service;

import cn.hutool.captcha.ICaptcha;
import com.lee.tms.modules.system.dto.body.AuthLoginBody;
import com.lee.tms.modules.system.dto.vo.AuthLoginVo;
import com.lee.tms.modules.system.dto.vo.AuthUserInfoVo;
import com.lee.tms.modules.system.dto.vo.FunctionMenuVo;

import java.util.List;

public interface AuthService
{
    ICaptcha createCaptcha(String uuid);

    void validateCaptcha(String uuid, String code);

    AuthLoginVo login(AuthLoginBody body);

    void logout();

    List<FunctionMenuVo> getCurrentUserMenus();

    AuthUserInfoVo getCurrentUserAuthInfo();
}
