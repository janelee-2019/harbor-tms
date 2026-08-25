package com.lee.tms.modules.system.service;

import com.lee.tms.modules.system.entity.ApiLog;

public interface LogService
{
    void addLoginLog(Long userId, String loginName, String loginStatus, String message);

    void addApiLog(ApiLog apiLog);
}
