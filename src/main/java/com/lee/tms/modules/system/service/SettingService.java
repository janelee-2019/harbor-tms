package com.lee.tms.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.tms.modules.system.entity.Setting;

public interface SettingService extends IService<Setting>
{
    Setting getSetting(String type, String key);

    void evictSetting(String type, String key);

    void clearCache();
}
