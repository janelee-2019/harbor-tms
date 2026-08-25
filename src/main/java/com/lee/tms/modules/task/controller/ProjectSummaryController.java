package com.lee.tms.modules.task.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.tms.infrastructure.log.ApiLog;
import com.lee.tms.infrastructure.rest.R;
import com.lee.tms.infrastructure.rest.body.DefaultOpByIdsBody;
import com.lee.tms.infrastructure.rest.query.DefaultInfoQuery;
import com.lee.tms.infrastructure.rest.vo.DefaultPageVo;
import com.lee.tms.modules.task.dto.body.ProjectSummaryAddBody;
import com.lee.tms.modules.task.dto.body.ProjectSummaryUpdateBody;
import com.lee.tms.modules.task.dto.query.ProjectSummaryPageQuery;
import com.lee.tms.modules.task.dto.vo.ProjectSummaryListItemVo;
import com.lee.tms.modules.task.dto.vo.ProjectSummaryVo;
import com.lee.tms.modules.task.entity.ProjectSummary;
import com.lee.tms.modules.task.service.ProjectSummaryService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/**
 * 项目摘要管理
 *
 * @author lee
 */
@RequiredArgsConstructor
@RestController
@RequestMapping ("/task/projectSummary")
public class ProjectSummaryController
{
    private final ProjectSummaryService projectSummaryService;

    /**
     * 添加项目摘要
     */
    @RequiresPermissions ("task:projectSummary:add")
    @ApiLog (module = "", tag = "新增ProjectSummary")
    @PostMapping ("add")
    public R insertProjectSummary(@Validated @RequestBody ProjectSummaryAddBody body)
    {
        projectSummaryService.insertProjectSummary(body);
        return R.ok();
    }

    /**
     * 更新项目摘要
     */
    @RequiresPermissions ("task:projectSummary:update")
    @ApiLog (module = "", tag = "更新ProjectSummary")
    @PostMapping ("update")
    public R updateProjectSummary(@Validated @RequestBody ProjectSummaryUpdateBody body)
    {
        projectSummaryService.updateProjectSummary(body);
        return R.ok();
    }

    /**
     * 删除项目摘要
     */
    @RequiresPermissions ("task:projectSummary:delete")
    @ApiLog (module = "", tag = "删除ProjectSummary")
    @PostMapping ("delete")
    public R deleteProjectSummary(@Validated @RequestBody DefaultOpByIdsBody body)
    {
        projectSummaryService.deleteProjectSummary(body.getIds());
        return R.ok();
    }

    /**
     * 获取项目摘要详情
     */
    @RequiresPermissions ("task:projectSummary:info")
    @GetMapping ("info")
    public R<ProjectSummaryVo> getProjectSummaryInfo(@Validated DefaultInfoQuery query)
    {
        return R.ok(ProjectSummaryVo.fromModel(projectSummaryService.getDetail(query.getId())));
    }

    /**
     * 获取项目摘要分页列表
     */
    @RequiresPermissions ("task:projectSummary:page")
    @GetMapping ("page")
    public R<DefaultPageVo<ProjectSummaryListItemVo>> getProjectSummarysPage(@Validated ProjectSummaryPageQuery query)
    {
        IPage<ProjectSummary> page = projectSummaryService.getPage(query);
        DefaultPageVo<ProjectSummaryListItemVo> vos = new DefaultPageVo<>(query.getPageNo(), query.getPageSize(), page.getTotal(), page
                .getRecords()
                .stream()
                .map(ProjectSummaryListItemVo::fromModel)
                .collect(Collectors.toList()));
        return R.ok(vos);
    }
}
