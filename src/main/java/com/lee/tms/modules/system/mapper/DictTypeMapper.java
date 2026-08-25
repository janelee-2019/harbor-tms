package com.lee.tms.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lee.tms.modules.system.entity.DictType;

import java.util.List;

public interface DictTypeMapper extends BaseMapper<DictType>
{
    List<DictType> selectAllDictTypes();

    DictType selectByDictKey(String dictKey);
}
