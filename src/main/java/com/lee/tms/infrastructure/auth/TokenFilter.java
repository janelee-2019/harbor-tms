package com.lee.tms.infrastructure.auth;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lee.tms.infrastructure.rest.R;
import com.lee.tms.infrastructure.rest.RestCode;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

public class TokenFilter extends AuthenticatingFilter
{
    private static final String TOKEN_KEY = "EDMS-Token";

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception
    {
        String token = obtainToken(request);
        if (StrUtil.isBlank(token))
        {
            return null;
        }
        return new AuthToken(token);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
    {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        // 优先放行 OPTIONS 跨域预检请求
        if (RequestMethod.OPTIONS
                .name()
                .equalsIgnoreCase(httpServletRequest.getMethod()))
        {
            return true;
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception
    {
        String token = obtainToken(request);
        // 如果未携带 Token，直接返回 JSON 错误响应并拦截
        if (StrUtil.isBlank(token))
        {
            writeJsonResponse(response, R.err(RestCode.INVALID_TOKEN));
            return false;
        }
        // 存在 Token 时，触发 Shiro 登录认证逻辑（内部自动调用 createToken）
        return executeLogin(request, response);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response)
    {
        // 认证/登录失败（如 Token 过期或签名无效），返回 JSON 并拦截
        writeJsonResponse(response, R.err(RestCode.INVALID_TOKEN));
        return false;
    }

    private String obtainToken(ServletRequest request)
    {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String token = httpRequest.getHeader(TOKEN_KEY);
        if (StrUtil.isBlank(token))
        {
            token = httpRequest.getParameter(TOKEN_KEY);
        }
        return token;
    }

    /**
     * 统一写出 JSON 异常响应
     */
    private void writeJsonResponse(ServletResponse response, Object body)
    {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json;charset=UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();

        try
        {
            String json = objectMapper.writeValueAsString(body);;
            httpResponse
                    .getWriter()
                    .print(json);
            httpResponse
                    .getWriter()
                    .flush();
        }
        catch (IOException ignored)
        {
        }
    }
}