package com.lee.tms.modules.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.tms.cache.DictService;
import com.lee.tms.modules.system.dto.vo.DictDataSimpleListVo;
import com.lee.tms.modules.system.entity.DictData;
import com.lee.tms.modules.system.mapper.DictDataMapper;
import com.lee.tms.modules.system.service.DictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DictDataServiceImpl extends ServiceImpl<DictDataMapper, DictData> implements DictDataService
{
    @Autowired
    private DictService dictService; // 注入字典缓存服务

    @Override
    public List<DictDataSimpleListVo> listDictDataByDictKey(String dictKey)
    {
        return dictService.getData(dictKey).stream().map(DictDataSimpleListVo::fromModel).collect(Collectors.toList());
    }

    @Override
    public String getDictLabel(String dictKey, String dictValue)
    {
        DictData data = dictService.getData(dictKey, dictValue);
        return data != null ? data.getDictLabel() : "";
    }

    @Override
    public String getDictValue(String dictKey, String dictLabel)
    {
        return dictService.getData(dictKey).stream().filter(d -> StrUtil.equals(dictLabel, d.getDictLabel())).map(DictData::getDictValue).findFirst().orElse("");
    }

    @Override
    public boolean checkDictDataExistByKeyAndValue(String dictKey, String dictValue)
    {
        return dictService.contains(dictKey, dictValue);
    }

    @Override
    public boolean checkDictDataExistByKeyAndLabel(String dictKey, String dictLabel)
    {
        return dictService.getData(dictKey).stream().anyMatch(vo -> StrUtil.equals(vo.getDictLabel(), dictLabel));
    }
}