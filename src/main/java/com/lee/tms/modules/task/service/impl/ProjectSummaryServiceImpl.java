package com.lee.tms.modules.task.service.impl;

import cn.hutool.core.bean.BeanUtil;import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.tms.infrastructure.auth.AuthContext;
import com.lee.tms.infrastructure.rest.RestCode;
import com.lee.tms.infrastructure.rest.RestException;
import com.lee.tms.modules.task.dto.body.ProjectSummaryAddBody;
import com.lee.tms.modules.task.dto.body.ProjectSummaryUpdateBody;
import com.lee.tms.modules.task.dto.query.ProjectSummaryQuery;
import com.lee.tms.modules.task.dto.query.ProjectSummaryPageQuery;
import com.lee.tms.modules.task.entity.ProjectSummary;
import com.lee.tms.modules.task.mapper.ProjectSummaryMapper;
import com.lee.tms.modules.task.service.ProjectSummaryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjectSummaryServiceImpl extends ServiceImpl<ProjectSummaryMapper, ProjectSummary> implements ProjectSummaryService
{
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertProjectSummary(ProjectSummaryAddBody body)
    {
        ProjectSummary entity = new ProjectSummary();
        BeanUtil.copyProperties(body, entity);
        entity.setCreateBy(AuthContext.getCurrentUserId());
        save(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateProjectSummary(ProjectSummaryUpdateBody body)
    {
        ProjectSummary entity = getDetail(body.getId());
        BeanUtil.copyProperties(body, entity);
        entity.setUpdateBy(AuthContext.getCurrentUserId());
        updateById(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteProjectSummary(List<Long> ids)
    {
        removeBatchByIds(ids);
    }

    @Override
    public ProjectSummary getDetail(Long id)
    {
        ProjectSummary entity = getById(id);
        if (entity == null)
        {
            throw new RestException(RestCode.SYS_ERR, "项目摘要表不存在 [ID = {}]", id);
        }
        return entity;
    }

    @Override
    public IPage<ProjectSummary> getPage(ProjectSummaryPageQuery query)
    {
        IPage<ProjectSummary> page = new Page<>(query.getPageNo(), query.getPageSize());
        LambdaQueryWrapper<ProjectSummary> wrapper = new LambdaQueryWrapper<>();

        if (StrUtil.isNotBlank(query.getKeyword()))
        {
            wrapper.and(w ->
                    w.like(ProjectSummary::getSn, query.getKeyword())
                    .or().like(ProjectSummary::getSrNo, query.getKeyword())
                    .or().like(ProjectSummary::getDcNo, query.getKeyword())
                    .or().like(ProjectSummary::getSrType, query.getKeyword())
                    .or().like(ProjectSummary::getTaskStatus, query.getKeyword())
                    .or().like(ProjectSummary::getSiteVisit, query.getKeyword())
                    .or().like(ProjectSummary::getStatus, query.getKeyword())
                    .or().like(ProjectSummary::getEpmCurrentStatus, query.getKeyword())
                    .or().like(ProjectSummary::getRemark, query.getKeyword())
                    .or().like(ProjectSummary::getIsDistributed, query.getKeyword())
                    .or().like(ProjectSummary::getType, query.getKeyword())
                    .or().like(ProjectSummary::getProjectTitle, query.getKeyword())
                    .or().like(ProjectSummary::getPoNo, query.getKeyword())
                    .or().like(ProjectSummary::getSpsManager, query.getKeyword())
                    );
        }
        return baseMapper.selectPage(page, wrapper);
    }
}