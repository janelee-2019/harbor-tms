package com.lee.tms.modules.system.controller;

import com.lee.tms.infrastructure.log.ApiLog;
import com.lee.tms.infrastructure.rest.R;
import com.lee.tms.modules.system.dto.body.AuthLoginBody;
import com.lee.tms.modules.system.dto.vo.AuthLoginVo;
import com.lee.tms.modules.system.service.AuthService;
import com.lee.tms.modules.system.service.LogService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * 鉴权管理
 */
@RestController
@RequestMapping ("sys/auth")
@RequiredArgsConstructor
public class AuthController
{
    private final AuthService authService;
    private final LogService logService;

    /**
     * 登录
     */
    @PostMapping ("login")
    public R login(@Validated @RequestBody AuthLoginBody body)
    {
        String loginStatus = "success";
        String message = "success";
        AuthLoginVo vo = null;
        try
        {
            //            authService.validateCaptcha(body.getUuid(), body.getCode());
            vo = authService.login(body);
            return R.ok(vo);
        }
        catch (Exception e)
        {
            loginStatus = "error";
            message = e.getMessage();
            throw e;
        }
        finally
        {
            logService.addLoginLog(vo == null ? null : vo.getId(), body.getLoginName(), loginStatus, message);
        }
    }

    /**
     * 登出
     */
    @ApiLog (module = "系统管理", tag = "用户注销")
    @GetMapping ("logout")
    public R logout()
    {
        authService.logout();
        return R.ok();
    }

    /**
     * 生成验证码
     */
    @GetMapping ("captcha.jpg")
    public void createCaptcha(HttpServletResponse response, @RequestParam String uuid) throws IOException
    {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        ServletOutputStream sos = response.getOutputStream();
        authService.createCaptcha(uuid).write(sos);
    }

    /**
     * 获取当前用户菜单
     */
    @GetMapping ("menus")
    public R getCurrentUserMenus()
    {
        return R.ok(authService.getCurrentUserMenus());
    }

    /**
     * 获取当前用户授权信息
     */
    @GetMapping ("user_info")
    public R getCurrentUserAuthInfo()
    {
        return R.ok(authService.getCurrentUserAuthInfo());
    }
}
