package com.lee.tms.modules.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.tms.infrastructure.rest.RestCode;
import com.lee.tms.infrastructure.rest.RestException;
import com.lee.tms.modules.system.entity.DictType;
import com.lee.tms.modules.system.mapper.DictTypeMapper;
import com.lee.tms.modules.system.service.DictTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictTypeServiceImpl extends ServiceImpl<DictTypeMapper, DictType> implements DictTypeService
{
    @Override
    public List<DictType> listAllDictTypes()
    {
        return baseMapper.selectAllDictTypes();
    }

    @Override
    public DictType getDictTypeByDictKey(String dictKey)
    {
        if (StrUtil.isBlank(dictKey))
        {
            return null;
        }
        DictType dictType = baseMapper.selectByDictKey(dictKey);
        if (dictType == null)
        {
            throw new RestException(RestCode.SYS_ERR, "字典不存在 [DictKey = {}]", dictKey);
        }
        return dictType;
    }
}