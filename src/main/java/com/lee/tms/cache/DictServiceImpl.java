package com.lee.tms.cache;

import cn.hutool.core.util.StrUtil;
import com.lee.tms.infrastructure.cache.CacheKey;
import com.lee.tms.infrastructure.cache.CacheService;
import com.lee.tms.modules.system.entity.DictData;
import com.lee.tms.modules.system.entity.DictType;
import com.lee.tms.modules.system.mapper.DictTypeMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DictServiceImpl implements DictService
{
    private final DictTypeMapper dictTypeMapper;
    private final CacheService cacheService;

    public DictServiceImpl(DictTypeMapper dictTypeMapper, CacheService cacheService)
    {
        this.dictTypeMapper = dictTypeMapper;
        this.cacheService = cacheService;
    }

    @Override
    public String getName(String dictKey)
    {
        DictType dictType = getDictType(dictKey);
        return dictType == null ? null : dictType.getDictName();
    }

    @Override
    public List<DictData> getData(String dictKey)
    {
        // 1. 当 dictKey 为空（null、"" 或纯空格）时，获取全部字典类型的字典明细
        if (StrUtil.isBlank(dictKey))
        {
            List<DictType> allDictTypes = dictTypeMapper.selectAllDictTypes();
            if (allDictTypes == null)
            {
                return List.of();
            }
            return allDictTypes
                    .stream()
                    .filter(dt -> dt.getDictDataList() != null)
                    .flatMap(dt -> dt
                            .getDictDataList()
                            .stream())
                    .collect(Collectors.toList());
        }

        // 2. 按指定 dictKey 查询单组字典明细
        DictType dictType = getDictType(dictKey);
        if (dictType == null || dictType.getDictDataList() == null)
        {
            return List.of();
        }
        return dictType.getDictDataList();
    }

    @Override
    public DictData getData(String dictKey, String value)
    {
        if (value == null)
        {
            return null;
        }
        return getData(dictKey)
                .stream()
                .filter(item -> value.equals(item.getDictValue()))
                .findFirst()
                .orElse(null);
    }

    private DictType getDictType(String dictKey)
    {
        if (dictKey == null)
        {
            return null;
        }

        // 1. 优先读取缓存
        DictType dictType = cacheService.get(CacheKey.SYS_DICT, dictKey, DictType.class);
        if (dictType != null)
        {
            return dictType;
        }

        // 2. 缓存未命中，单条精确查询数据库
        dictType = dictTypeMapper.selectByDictKey(dictKey);
        if (dictType == null)
        {
            return null;
        }

        // 3. 写入缓存
        cacheService.put(CacheKey.SYS_DICT, dictKey, dictType, DictType.class);
        return dictType;
    }


    @Override
    public boolean contains(String dictKey, String value)
    {
        return getData(dictKey, value) != null;
    }

    @Override
    public void evict(String dictKey)
    {
        cacheService.evict(CacheKey.SYS_DICT, dictKey, DictType.class);
    }

    @Override
    public void clearCache()
    {
        cacheService.clear(CacheKey.SYS_DICT);
    }
}