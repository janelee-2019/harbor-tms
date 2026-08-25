package com.lee.tms.modules.task.dto.vo;

import cn.hutool.core.bean.BeanUtil;
import com.lee.tms.modules.task.entity.Batch;
import lombok.Data;

@Data
public final class BatchVo extends BatchListItemVo
{
    public static BatchVo fromModel(Batch e)
    {
        BatchVo vo = new BatchVo();
        BeanUtil.copyProperties(e, vo);
        return vo;
    }
}