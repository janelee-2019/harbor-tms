package com.lee.tms.modules.system.dto.vo;

import cn.hutool.core.bean.BeanUtil;
import com.lee.tms.modules.system.entity.DictData;
import lombok.Data;

@Data
public final class DictDataSimpleListVo
{
    private String dictLabel;

    private String dictValue;

    public static DictDataSimpleListVo fromModel(DictData dictData)
    {
        DictDataSimpleListVo vo = new DictDataSimpleListVo();
        BeanUtil.copyProperties(dictData, vo);
        return vo;
    }
}
