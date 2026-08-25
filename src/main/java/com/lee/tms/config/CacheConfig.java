package com.lee.tms.config;

import com.lee.tms.infrastructure.cache.CacheKey;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CacheConfig
{
    @Bean (destroyMethod = "close")
    public CacheManager ehcacheManager()
    {
        // 采用纯堆内内存 (heap 1000条)，无需 offheap 序列化校验，性能更高且极度稳定
        ResourcePoolsBuilder pools = ResourcePoolsBuilder.heap(1000);

        CacheConfigurationBuilder<Object, Object> cacheConfig = CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class, pools);

        return CacheManagerBuilder
                .newCacheManagerBuilder()
                .withCache(CacheKey.SYS_DICT, cacheConfig)
                .withCache(CacheKey.SYS_SETTING, cacheConfig)
                .build(true);
    }
}