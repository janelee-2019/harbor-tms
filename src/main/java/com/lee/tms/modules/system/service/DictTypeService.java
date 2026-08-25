package com.lee.tms.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.tms.modules.system.entity.DictType;

import java.util.List;

public interface DictTypeService extends IService<DictType>
{
    List<DictType> listAllDictTypes();

    DictType getDictTypeByDictKey(String dictKey);
}
