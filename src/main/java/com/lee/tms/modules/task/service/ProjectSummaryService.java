package com.lee.tms.modules.task.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.tms.modules.task.dto.body.ProjectSummaryAddBody;
import com.lee.tms.modules.task.dto.body.ProjectSummaryUpdateBody;
import com.lee.tms.modules.task.dto.query.ProjectSummaryPageQuery;
import com.lee.tms.modules.task.entity.ProjectSummary;

import java.util.List;

/**
 * 项目摘要服务
 */
public interface ProjectSummaryService extends IService<ProjectSummary>
{
    void insertProjectSummary(ProjectSummaryAddBody body);

    void updateProjectSummary(ProjectSummaryUpdateBody body);

    void deleteProjectSummary(List<Long> ids);


    ProjectSummary getDetail(Long id);

    IPage<ProjectSummary> getPage(ProjectSummaryPageQuery query);
}