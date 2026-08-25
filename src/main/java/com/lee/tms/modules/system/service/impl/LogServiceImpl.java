package com.lee.tms.modules.system.service.impl;

import com.lee.tms.infrastructure.util.IpUtil;
import com.lee.tms.infrastructure.util.ServletUtil;
import com.lee.tms.modules.system.entity.ApiLog;
import com.lee.tms.modules.system.entity.LoginLog;
import com.lee.tms.modules.system.service.LogService;
import eu.bitwalker.useragentutils.UserAgent;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.Executor;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService
{
    @Qualifier ("asyncLoggingExecutor")
    private final Executor asyncLoggingExecutor;

    @Override
    public void addLoginLog(Long userId, String loginName, String loginStatus, String message)
    {
        HttpServletRequest request = ServletUtil.getRequest();
        final String ua = request.getHeader("User-Agent");
        final String ip = IpUtil.getIpAddr(request);

        asyncLoggingExecutor.execute(() -> {
            LoginLog loginLog = new LoginLog();
            loginLog.setLoginName(loginName);
            loginLog.setLoginStatus(loginStatus);
            loginLog.setMessage(message);
            loginLog.setLoginDate(LocalDateTime.now());
            loginLog.setClientIp(ip);
            loginLog.setUserAgent(ua);

            UserAgent uaObj = UserAgent.parseUserAgentString(ua);

            loginLog.setClientBrowser(uaObj.getBrowser().getName());
            loginLog.setClientOs(uaObj.getOperatingSystem().getName());
            loginLog.setUserId(userId);

            loginLog.insert();
        });
    }

    @Async ("asyncLoggingExecutor")
    @Override
    public void addApiLog(ApiLog apiLog)
    {
        apiLog.insert();
    }
}