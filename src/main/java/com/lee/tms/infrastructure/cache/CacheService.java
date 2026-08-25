package com.lee.tms.infrastructure.cache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.springframework.stereotype.Service;

/**
 * 对 Ehcache 进行统一封装。 业务代码不直接操作 Ehcache。
 */
@Service
public class CacheService
{
    private final CacheManager cacheManager;

    public CacheService(CacheManager cacheManager)
    {
        this.cacheManager = cacheManager;
    }

    /**
     * 获取缓存。
     */
    public <K, V> V get(String cacheName, K key, Class<V> valueType)
    {
        Cache<Object, Object> cache = getCache(cacheName);
        if (cache == null)
        {
            return null;
        }
        Object value = cache.get(key);
        // 使用 Class 动态判定类型，安全强转，规避 Ehcache 强类型校验报错
        if (valueType != null && valueType.isInstance(value))
        {
            return valueType.cast(value);
        }
        return null;
    }

    /**
     * 写入缓存。
     */
    public <K, V> void put(String cacheName, K key, V value, Class<V> valueType)
    {
        Cache<Object, Object> cache = getCache(cacheName);
        if (cache != null && key != null && value != null)
        {
            cache.put(key, value);
        }
    }

    /**
     * 清除单个 Key
     */
    public <K, V> void evict(String cacheName, K key, Class<V> valueType)
    {
        Cache<Object, Object> cache = getCache(cacheName);
        if (cache != null && key != null)
        {
            cache.remove(key);
        }
    }

    /**
     * 清空指定名称的缓存
     */
    public void clear(String cacheName)
    {
        Cache<Object, Object> cache = getCache(cacheName);
        if (cache != null)
        {
            cache.clear();
        }
    }

    /**
     * 统一以 <Object, Object> 读取 Cache，规避 Ehcache 3 getCache 时的类型匹配异常
     */
    private Cache<Object, Object> getCache(String cacheName)
    {
        return cacheManager.getCache(cacheName, Object.class, Object.class);
    }
}