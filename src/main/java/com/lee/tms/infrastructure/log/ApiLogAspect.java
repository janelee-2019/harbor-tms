package com.lee.tms.infrastructure.log;

import cn.hutool.core.util.ArrayUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lee.tms.infrastructure.auth.AuthContext;
import com.lee.tms.infrastructure.auth.AuthUser;
import com.lee.tms.infrastructure.util.IpUtil;
import com.lee.tms.infrastructure.util.ServletUtil;
import com.lee.tms.modules.system.service.LogService;
import eu.bitwalker.useragentutils.UserAgent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.Map;

@Aspect
@Component
public class ApiLogAspect
{
    @Lazy
    @Autowired
    private LogService logService;

    @Autowired
    private ObjectMapper objectMapper;

    @Pointcut ("@annotation(com.lee.tms.infrastructure.log.ApiLog)")
    public void pointCut()
    {
    }

    @Around ("pointCut()")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable
    {
        Object result;
        com.lee.tms.modules.system.entity.ApiLog entity = new com.lee.tms.modules.system.entity.ApiLog();
        prepareAddLog(joinPoint, entity);
        long startTime = System.currentTimeMillis();

        ObjectMapper objectMapper = new ObjectMapper();

        try
        {
            result = joinPoint.proceed();
            // 代理方法正常返回
            String json = objectMapper.writeValueAsString(result);
            entity.setJsonResult(json);
            entity.setStatus("success");
            entity.setMessage("success");
        }
        catch (Throwable ex)
        {
            // 代理方法抛出异常
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter, true);
            ex.printStackTrace(printWriter);
            entity.setJsonResult(stringWriter.toString());
            entity.setStatus("error");
            entity.setMessage(ex.getMessage());
            throw ex;
        }
        finally
        {
            long endTime = System.currentTimeMillis();
            entity.setDuration(endTime - startTime);
            logService.addApiLog(entity);
        }

        return result;
    }

    private void prepareAddLog(JoinPoint joinPoint, com.lee.tms.modules.system.entity.ApiLog entity) throws JsonProcessingException
    {
        ApiLog annotation = getAnnotation(joinPoint);
        if (annotation == null)
        {
            return;
        }

        AuthUser authUser = AuthContext.getAuthUser();
        HttpServletRequest request = ServletUtil.getRequest();

        String ip = IpUtil.getIpAddr(request);
        String ua = request.getHeader("User-Agent");

        UserAgent uaObj = UserAgent.parseUserAgentString(ua);

        entity.setCreateDate(LocalDateTime.now());
        entity.setUserId(authUser.getId());
        entity.setHttpMethod(request.getMethod());
        entity.setRequestUrl(request.getRequestURL().toString());
        entity.setClientIp(ip);
        entity.setClientOs(uaObj.getOperatingSystem().getName());
        entity.setUserAgent(ua);
        entity.setClientBrowser(uaObj.getBrowser().getName());
        entity.setTag(annotation.tag());
        entity.setRequestParam(getRequestParam(joinPoint, request));
        entity.setModule(annotation.module());
    }

    private String getRequestParam(JoinPoint joinPoint, HttpServletRequest request) throws JsonProcessingException
    {
        if (HttpMethod.GET.name().equals(request.getMethod()))
        {
            Map<?, ?> params = (Map<?, ?>) ServletUtil.getRequest().getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            return objectMapper.writeValueAsString(params);
        }
        else if (HttpMethod.POST.name().equals(request.getMethod()))
        {
            return args2Json(joinPoint.getArgs());
        }
        return "";
    }

    private String args2Json(Object[] args) throws JsonProcessingException
    {
        if (ArrayUtil.isEmpty(args))
        {
            return "";
        }

        Object arg = args[0];

        if (ignoreArg(arg))
        {
            return "";
        }

        return objectMapper.writeValueAsString(arg);
    }

    private boolean ignoreArg(final Object arg)
    {
        return arg instanceof MultipartFile || arg instanceof HttpServletRequest || arg instanceof HttpServletResponse;
    }

    private ApiLog getAnnotation(JoinPoint joinPoint)
    {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        return methodSignature.getMethod().getAnnotation(ApiLog.class);
    }
}
