package com.lee.tms.modules.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.tms.infrastructure.cache.CacheKey;
import com.lee.tms.infrastructure.cache.CacheService;
import com.lee.tms.infrastructure.rest.RestCode;
import com.lee.tms.infrastructure.rest.RestException;
import com.lee.tms.modules.system.entity.Setting;
import com.lee.tms.modules.system.mapper.SettingMapper;
import com.lee.tms.modules.system.service.SettingService;
import org.springframework.stereotype.Service;

@Service
public class SettingServiceImpl extends ServiceImpl<SettingMapper, Setting> implements SettingService
{
    private final CacheService cacheService;

    public SettingServiceImpl(CacheService cacheService)
    {
        this.cacheService = cacheService;
    }

    @Override
    public Setting getSetting(String type, String key)
    {
        if (type == null || key == null)
        {
            throw new RestException(RestCode.SYS_ERR, "配置参数不能为空");
        }

        String itemKey = type + ":" + key;

        // 1. 优先从缓存读取
        Setting setting = cacheService.get(CacheKey.SYS_SETTING, itemKey, Setting.class);

        if (setting != null)
        {
            return setting;
        }

        // 2. 缓存未命中，查询数据库
        setting = getOne(Wrappers.<Setting>lambdaQuery().eq(Setting::getType, type).eq(Setting::getKey, key));

        if (setting == null)
        {
            throw new RestException(RestCode.SYS_ERR, "系统配置不存在 [type = {}, key = {}]", type, key);
        }

        // 3. 写入缓存
        cacheService.put(CacheKey.SYS_SETTING, itemKey, setting, Setting.class);

        return setting;
    }

    @Override
    public void evictSetting(String type, String key)
    {
        String itemKey = type + ":" + key;
        cacheService.evict(CacheKey.SYS_SETTING, itemKey, Setting.class);
    }

    @Override
    public void clearCache()
    {
        cacheService.clear(CacheKey.SYS_SETTING);
    }
}