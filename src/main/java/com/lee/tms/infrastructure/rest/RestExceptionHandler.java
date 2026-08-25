package com.lee.tms.infrastructure.rest;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler
{
    @ExceptionHandler (NoHandlerFoundException.class)
    public R handleNoFoundException(NoHandlerFoundException e)
    {
        log.error(e.getMessage());
        return R.err(RestCode.REQ_ERR, "路径不存在 [{}]", e.getRequestURL());
    }

    @ExceptionHandler (HttpRequestMethodNotSupportedException.class)
    public R handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e)
    {
        log.error(e.getMessage());
        return R.err(RestCode.REQ_ERR, "请求方法不支持 [{}]", e.getMethod());
    }

    @ExceptionHandler (HttpMessageNotReadableException.class)
    public R handleHttpMessageNotReadableException(HttpMessageNotReadableException e)
    {
        log.error(e.getMessage());
        return R.err(RestCode.REQ_ERR, "无法读取请求体");
    }

    @ExceptionHandler (MissingServletRequestParameterException.class)
    public R handleMissingServletRequestParameterException(MissingServletRequestParameterException e)
    {
        log.error(e.getMessage());
        return R.err(RestCode.REQ_ERR, "缺少查询参数 [{}]", e.getParameterName());
    }

    @ExceptionHandler (MissingServletRequestPartException.class)
    public R handleMissingServletRequestPartException(MissingServletRequestPartException e)
    {
        log.error(e.getMessage());
        return R.err(RestCode.REQ_ERR, "缺少请求体参数 [{}]", e.getRequestPartName());
    }

    @ExceptionHandler (MethodArgumentNotValidException.class)
    public R handleMethodArgumentNotValidException(MethodArgumentNotValidException e)
    {
        log.error(e.getMessage());
        StringBuilder sb = new StringBuilder();
        e.getBindingResult().getFieldErrors().forEach(fe -> sb.append("{").append(fe.getDefaultMessage()).append("} "));
        return R.err(RestCode.REQ_ERR, sb.toString());
    }

    @ExceptionHandler (BindException.class)
    public R handleBindException(BindException e)
    {
        log.error(e.getMessage());
        StringBuilder sb = new StringBuilder();
        e.getBindingResult().getFieldErrors().forEach(fe -> sb.append("{").append(fe.getDefaultMessage()).append("} "));
        return R.err(RestCode.REQ_ERR, sb.toString());
    }

    @ExceptionHandler (UnauthorizedException.class)
    public R handleUnauthorizedException(UnauthorizedException e)
    {
        log.error(e.getMessage());
        return R.err(RestCode.SYS_ERR, "当前用户无权操作");
    }

    @ExceptionHandler (RestException.class)
    public R handleRestException(RestException e, HttpServletRequest request)
    {
        log.error("RestException: [{}]", e.getMessage(), e);
        log.error("Request UA: [{}]", request.getHeader("User-Agent"));
        return R.err(e);
    }

    @ExceptionHandler (Exception.class)
    public R handleException(Exception e)
    {
        log.error(e.getMessage(), e);
        return R.err(RestCode.SYS_ERR, e.getMessage());
    }
}
