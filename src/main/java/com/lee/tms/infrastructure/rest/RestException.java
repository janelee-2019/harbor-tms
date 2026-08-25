package com.lee.tms.infrastructure.rest;

import cn.hutool.core.util.StrUtil;

public final class RestException extends RuntimeException
{
    private final Integer code;
    private String message = "";

    public RestException(RestCode restCode)
    {
        this.message = restCode.getMessage();
        this.code = restCode.getCode();
    }

    public RestException(RestCode restCode, String tail, Object... params)
    {
        this.message = restCode.getMessage() + ": " + StrUtil.format(tail, params);
        this.code = restCode.getCode();
    }

    public RestException(String tail, Object... params)
    {
        this.message = StrUtil.format(tail, params);
        this.code = RestCode.SYS_ERR.getCode();
    }

    public RestException(RestCode restCode, Throwable e)
    {
        this.message = restCode.getMessage() + "，" + e.getMessage();
        this.code = restCode.getCode();
    }

    public Integer getCode()
    {
        return code;
    }

    @Override
    public String getMessage()
    {
        return message;
    }
}
