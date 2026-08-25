package com.lee.tms.cache;

import com.lee.tms.modules.system.entity.DictData;

import java.util.List;

public interface DictService
{
    /**
     * 根据字典 Key 获取字典名称。
     */
    String getName(String dictKey);

    /**
     * 根据字典 Key 查询全部字典数据。
     */
    List<DictData> getData(String dictKey);

    /**
     * 根据字典 Key 和字典 Value 查询字典数据。
     */
    DictData getData(String dictKey, String value);

    /**
     * 根据字典 Key 和 Value 判断是否存在。
     */
    boolean contains(String dictKey, String value);

    /**
     * 清除指定字典缓存。
     */
    void evict(String dictKey);

    /**
     * 清除全部字典缓存。
     */
    void clearCache();
}