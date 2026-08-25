package com.lee.tms.modules.task.dto.vo;

import cn.hutool.core.bean.BeanUtil;
import com.lee.tms.modules.task.entity.ProjectSummary;
import lombok.Data;

@Data
public final class ProjectSummaryVo extends ProjectSummaryListItemVo
{
    public static ProjectSummaryVo fromModel(ProjectSummary e)
    {
        ProjectSummaryVo vo = new ProjectSummaryVo();
        BeanUtil.copyProperties(e, vo);
        return vo;
    }
}