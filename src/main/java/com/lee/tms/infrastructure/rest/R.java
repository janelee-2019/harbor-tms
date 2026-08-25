package com.lee.tms.infrastructure.rest;

import cn.hutool.core.util.StrUtil;
import lombok.Data;

@Data
public final class R<T>
{
    /**
     * 返回状态标志
     */
    private Boolean status;
    /**
     * 返回状态码
     */
    private Integer code;
    /**
     * 返回消息
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    private R()
    {
        this.status = RestCode.SUCCESS.getStatus();
        this.code = RestCode.SUCCESS.getCode();
        this.message = RestCode.SUCCESS.getMessage();
    }

    private R(T t)
    {
        this.status = RestCode.SUCCESS.getStatus();
        this.code = RestCode.SUCCESS.getCode();
        this.message = RestCode.SUCCESS.getMessage();
        this.data = t;
    }

    private R(RestCode restCode)
    {
        this.status = restCode.getStatus();
        this.code = restCode.getCode();
        this.message = restCode.getMessage();
    }

    private R(RestCode restCode, T t)
    {
        this.status = restCode.getStatus();
        this.code = restCode.getCode();
        this.message = restCode.getMessage();
        this.data = t;
    }

    private R(RestCode restCode, String tail, Object... params)
    {
        this.status = restCode.getStatus();
        this.code = restCode.getCode();
        this.message = restCode.getMessage() + ": " + StrUtil.format(tail, params);
    }

    private R(RestException e)
    {
        this.status = false;
        this.code = e.getCode();
        this.message = e.getMessage();
    }

    public static R ok()
    {
        return new R();
    }

    public static <T> R<T> ok(T t)
    {
        return new R(t);
    }

    public static R err(RestCode restCode)
    {
        return new R(restCode);
    }

    public static R err(RestCode restCode, String tail, Object... params)
    {
        return new R(restCode, tail, params);
    }

    public static R err(RestException e)
    {
        return new R(e);
    }
}

