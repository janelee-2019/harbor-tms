package com.lee.tms.modules.task.dto.vo;

import cn.hutool.core.bean.BeanUtil;
import com.lee.tms.modules.task.entity.Drawing;
import lombok.Data;

@Data
public final class DrawingVo extends DrawingListItemVo
{
    public static DrawingVo fromModel(Drawing e)
    {
        DrawingVo vo = new DrawingVo();
        BeanUtil.copyProperties(e, vo);
        return vo;
    }
}