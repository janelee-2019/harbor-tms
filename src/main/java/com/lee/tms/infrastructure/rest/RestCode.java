package com.lee.tms.infrastructure.rest;

public enum RestCode
{
    // 设置返回码
    SUCCESS(true, 0, "success"),
    ERROR(false, -1, "error"),
    REQ_ERR(false, 1000, "请求异常"),
    SYS_ERR(false, 2000, "系统异常"),
    INVALID_TOKEN(false, 2001, "用户令牌无效"),
    INVALID_CAPTCHA(false, 2002, "图形验证码不正确"),
    INVALID_LOGIN_CREDENTIALS(false, 2003, "用户名或密码错误"),
    SYS_BUSY(false, 2004, "系统繁忙，请稍候重试")
    ;

    private final Boolean status;
    private final Integer code;
    private final String message;

    RestCode(Boolean status, Integer code, String message)
    {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public Boolean getStatus()
    {
        return status;
    }

    public Integer getCode()
    {
        return code;
    }

    public String getMessage()
    {
        return message;
    }
}

