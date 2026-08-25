package com.lee.tms.modules.task.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.tms.domain.drawing.DrawingStatus;
import com.lee.tms.infrastructure.auth.AuthContext;
import com.lee.tms.infrastructure.auth.AuthUser;
import com.lee.tms.infrastructure.rest.RestCode;
import com.lee.tms.infrastructure.rest.RestException;
import com.lee.tms.modules.system.service.UserService;
import com.lee.tms.modules.task.dto.body.DrawingAddBody;
import com.lee.tms.modules.task.dto.body.DrawingUpdateBody;
import com.lee.tms.modules.task.dto.query.DrawingPageQuery;
import com.lee.tms.modules.task.dto.body.DrawingTaskIssueBody;
import com.lee.tms.modules.task.dto.body.DrawingTaskSubmitPassedBody;
import com.lee.tms.modules.task.entity.Drawing;
import com.lee.tms.modules.task.mapper.DrawingMapper;
import com.lee.tms.modules.task.service.BatchService;
import com.lee.tms.modules.task.service.DrawingService;
import com.lee.tms.modules.task.service.ProjectSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DrawingServiceImpl extends ServiceImpl<DrawingMapper, Drawing> implements DrawingService
{
    @Lazy
    @Autowired
    private BatchService batchService;

    @Lazy
    @Autowired
    private UserService userService;

    @Lazy
    @Autowired
    private ProjectSummaryService projectSummaryService;

    @Transactional (rollbackFor = Exception.class)
    @Override
    public void insertDrawing(DrawingAddBody body)
    {
        Drawing entity = new Drawing();
        BeanUtil.copyProperties(body, entity);
        entity.setCreateBy(AuthContext.getCurrentUserId());
        save(entity);
    }

    @Transactional (rollbackFor = Exception.class)
    @Override
    public void updateDrawing(DrawingUpdateBody body)
    {
        Drawing entity = getDetail(body.getId());
        BeanUtil.copyProperties(body, entity);
        entity.setUpdateBy(AuthContext.getCurrentUserId());
        updateById(entity);
    }

    @Transactional (rollbackFor = Exception.class)
    @Override
    public void deleteDrawing(List<Long> ids)
    {
        removeBatchByIds(ids);
    }


    @Transactional (rollbackFor = Exception.class)
    @Override
    public void issueDraftTask(DrawingTaskIssueBody body)
    {
        AuthUser authUser = AuthContext.getAuthUser();

        if (body.getUserId() != null)
        {
            if (userService.getUserInfo(body.getUserId()) == null)
                throw new RestException("绘图员不存在 [ID = {}]", body.getUserId());
        }

        List<Drawing> drawings = list(Wrappers.<Drawing>lambdaQuery().in(Drawing::getId, body.getIds()));

        LocalDateTime now = LocalDateTime.now();

        for (Drawing drawing : drawings)
        {
            drawing.setDrawingStatus(DrawingStatus.DRAFT_ISSUED.getValue());
            drawing.setDrafterId(body.getUserId());
            drawing.setDraftIssuerId(authUser.getId());
            drawing.setDraftIssuedDate(now);
            drawing.setUpdateBy(authUser.getId());

            updateById(drawing);
        }
    }

    @Transactional (rollbackFor = Exception.class)
    @Override
    public void issueProofreadTask(DrawingTaskIssueBody body)
    {
        AuthUser authUser = AuthContext.getAuthUser();

        if (body.getUserId() != null)
        {
            if (userService.getUserInfo(body.getUserId()) == null)
                throw new RestException("校对员不存在 [ID = {}]", body.getUserId());
        }

        List<Drawing> drawings = list(Wrappers.<Drawing>lambdaQuery().in(Drawing::getId, body.getIds()));

        LocalDateTime now = LocalDateTime.now();

        for (Drawing drawing : drawings)
        {
            drawing.setDrawingStatus(DrawingStatus.PROOFREAD_ISSUED.getValue());
            drawing.setProofreaderId(body.getUserId());
            drawing.setProofreadIssuerId(authUser.getId());
            drawing.setProofreadIssuedDate(now);
            drawing.setUpdateBy(authUser.getId());

            updateById(drawing);
        }
    }

    @Transactional (rollbackFor = Exception.class)
    @Override
    public void issueQcTask(DrawingTaskIssueBody body)
    {
        AuthUser authUser = AuthContext.getAuthUser();

        if (body.getUserId() != null)
        {
            if (userService.getUserInfo(body.getUserId()) == null)
                throw new RestException("初级质检员不存在 [ID = {}]", body.getUserId());
        }

        List<Drawing> drawings = list(Wrappers.<Drawing>lambdaQuery().in(Drawing::getId, body.getIds()));

        LocalDateTime now = LocalDateTime.now();

        for (Drawing drawing : drawings)
        {
            drawing.setDrawingStatus(DrawingStatus.QC_ISSUED.getValue());
            drawing.setQcId(body.getUserId());
            drawing.setQcIssuerId(authUser.getId());
            drawing.setQcIssuedDate(now);
            drawing.setUpdateBy(authUser.getId());

            updateById(drawing);
        }
    }

    @Transactional (rollbackFor = Exception.class)
    @Override
    public void issueSqcTask(DrawingTaskIssueBody body)
    {
        AuthUser authUser = AuthContext.getAuthUser();

        if (body.getUserId() != null)
        {
            if (userService.getUserInfo(body.getUserId()) == null)
                throw new RestException("高级质检员不存在 [ID = {}]", body.getUserId());
        }

        List<Drawing> drawings = list(Wrappers.<Drawing>lambdaQuery().in(Drawing::getId, body.getIds()));

        LocalDateTime now = LocalDateTime.now();

        for (Drawing drawing : drawings)
        {
            drawing.setDrawingStatus(DrawingStatus.SQC_ISSUED.getValue());
            drawing.setSqcId(body.getUserId());
            drawing.setSqcIssuerId(authUser.getId());
            drawing.setSqcIssuedDate(now);
            drawing.setUpdateBy(authUser.getId());

            updateById(drawing);
        }
    }

    private void verifyOpPemission(Long opUserId)
    {
        if (AuthContext.getAuthUser().notLeader())
        {
            if (!AuthContext.getAuthUser().getId().equals(opUserId))
            {
                throw new RestException("只能操作自己的任务");
            }
        }
    }

    @Transactional (rollbackFor = Exception.class)
    @Override
    public void receiveDraftTask(List<Long> drawingIds)
    {
        AuthUser authUser = AuthContext.getAuthUser();
        List<Drawing> drawings = listByIds(drawingIds);
        LocalDateTime now = LocalDateTime.now();

        for (Drawing drawing : drawings)
        {
            verifyOpPemission(drawing.getDrafterId());

            drawing.setDrawingStatus(DrawingStatus.DRAFT_RECEIVED.getValue());
            drawing.setDraftReceivedDate(now);
            drawing.setUpdateBy(authUser.getId());

            updateById(drawing);
        }
    }

    @Transactional (rollbackFor = Exception.class)
    @Override
    public void receiveProofreadTask(List<Long> drawingIds)
    {
        AuthUser authUser = AuthContext.getAuthUser();
        List<Drawing> drawings = listByIds(drawingIds);
        LocalDateTime now = LocalDateTime.now();

        for (Drawing drawing : drawings)
        {
            verifyOpPemission(drawing.getProofreaderId());

            drawing.setDrawingStatus(DrawingStatus.PROOFREAD_RECEIVED.getValue());
            drawing.setProofreadReceivedDate(now);
            drawing.setUpdateBy(authUser.getId());

            updateById(drawing);
        }
    }

    @Transactional (rollbackFor = Exception.class)
    @Override
    public void receiveQcTask(List<Long> drawingIds)
    {
        AuthUser authUser = AuthContext.getAuthUser();
        List<Drawing> drawings = listByIds(drawingIds);
        LocalDateTime now = LocalDateTime.now();

        for (Drawing drawing : drawings)
        {
            verifyOpPemission(drawing.getQcId());

            drawing.setDrawingStatus(DrawingStatus.QC_RECEIVED.getValue());
            drawing.setQcReceivedDate(now);
            drawing.setUpdateBy(authUser.getId());

            updateById(drawing);
        }
    }

    @Transactional (rollbackFor = Exception.class)
    @Override
    public void receiveSqcTask(List<Long> drawingIds)
    {
        AuthUser authUser = AuthContext.getAuthUser();
        List<Drawing> drawings = listByIds(drawingIds);
        LocalDateTime now = LocalDateTime.now();

        for (Drawing drawing : drawings)
        {
            verifyOpPemission(drawing.getSqcId());

            drawing.setDrawingStatus(DrawingStatus.SQC_RECEIVED.getValue());
            drawing.setSqcReceivedDate(now);
            drawing.setUpdateBy(authUser.getId());

            updateById(drawing);
        }
    }

    @Transactional (rollbackFor = Exception.class)
    @Override
    public void submitDraftTask(List<Long> drawingIds)
    {
        AuthUser authUser = AuthContext.getAuthUser();
        List<Drawing> drawings = listByIds(drawingIds);
        LocalDateTime now = LocalDateTime.now();

        for (Drawing drawing : drawings)
        {
            verifyOpPemission(drawing.getDrafterId());

            drawing.setDrawingStatus(DrawingStatus.DRAFT_SUBMITTED.getValue());
            drawing.setDraftSubmittedDate(now);
            drawing.setUpdateBy(authUser.getId());

            updateById(drawing);
        }
    }

    @Transactional (rollbackFor = Exception.class)
    @Override
    public void submitProofreadTask(DrawingTaskSubmitPassedBody body)
    {
        AuthUser authUser = AuthContext.getAuthUser();
        List<Drawing> drawings = listByIds(body.getIds());
        LocalDateTime now = LocalDateTime.now();

        for (Drawing drawing : drawings)
        {
            verifyOpPemission(drawing.getProofreaderId());

            drawing.setDrawingStatus(DrawingStatus.PROOFREAD_SUBMITTED.getValue());
            if (Boolean.TRUE.equals(body.getIsPassed()))
            {
                drawing.setProofreadSubmittedDate(now);
            }
            else
            {
                drawing.setDraftReceivedDate(null);
                drawing.setDraftSubmittedDate(null);
                drawing.setProofreadReceivedDate(null);
                drawing.setProofreadSubmittedDate(null);
            }
            drawing.setUpdateBy(authUser.getId());

            updateById(drawing);
        }
    }

    @Transactional (rollbackFor = Exception.class)
    @Override
    public void submitQcTask(DrawingTaskSubmitPassedBody body)
    {
        AuthUser authUser = AuthContext.getAuthUser();
        List<Drawing> drawings = listByIds(body.getIds());
        LocalDateTime now = LocalDateTime.now();

        for (Drawing drawing : drawings)
        {
            // verifyOpPemission(drawing.getQcId());

            drawing.setDrawingStatus(DrawingStatus.QC_SUBMITTED.getValue());
            if (Boolean.TRUE.equals(body.getIsPassed()))
            {
                drawing.setQcSubmittedDate(now);
            }
            else
            {
                drawing.setProofreadReceivedDate(null);
                drawing.setProofreadSubmittedDate(null);
                drawing.setQcReceivedDate(null);
                drawing.setQcSubmittedDate(null);
            }
            drawing.setUpdateBy(authUser.getId());

            updateById(drawing);
        }
    }

    @Transactional (rollbackFor = Exception.class)
    @Override
    public void submitSqcTask(DrawingTaskSubmitPassedBody body)
    {
        AuthUser authUser = AuthContext.getAuthUser();
        List<Drawing> drawings = listByIds(body.getIds());
        LocalDateTime now = LocalDateTime.now();

        for (Drawing drawing : drawings)
        {
            verifyOpPemission(drawing.getSqcId());

            drawing.setDrawingStatus(DrawingStatus.SQC_SUBMITTED.getValue());
            if (Boolean.TRUE.equals(body.getIsPassed()))
            {
                drawing.setSqcSubmittedDate(now);
            }
            else
            {
                drawing.setQcReceivedDate(null);
                drawing.setQcSubmittedDate(null);
                drawing.setSqcReceivedDate(null);
                drawing.setSqcSubmittedDate(null);
            }
            drawing.setUpdateBy(authUser.getId());

            updateById(drawing);
        }
    }


    @Override
    public Drawing getDetail(Long id)
    {
        Drawing entity = getById(id);
        if (entity == null)
        {
            throw new RestException(RestCode.SYS_ERR, "图纸信息表不存在 [ID = {}]", id);
        }
        return entity;
    }

    @Override
    public IPage<Drawing> getPage(DrawingPageQuery query)
    {
        IPage<Drawing> page = new Page<>(query.getPageNo(), query.getPageSize());
        LambdaQueryWrapper<Drawing> wrapper = new LambdaQueryWrapper<>();

        if (StrUtil.isNotBlank(query.getKeyword()))
        {
            wrapper.and(w -> w.like(Drawing::getSn, query.getKeyword()).or().like(Drawing::getPackageNo, query.getKeyword()).or().like(Drawing::getBatchNo, query.getKeyword()).or()
                              .like(Drawing::getDrawingFileName, query.getKeyword()).or().like(Drawing::getDrawingNo, query.getKeyword()).or().like(Drawing::getDrawingFileDir, query.getKeyword()).or()
                              .like(Drawing::getDrawingCategory, query.getKeyword()).or().like(Drawing::getDrawingType, query.getKeyword()).or().like(Drawing::getDrawingStatus, query.getKeyword())
                              .or().like(Drawing::getRemark, query.getKeyword()).or().like(Drawing::getDcNo, query.getKeyword()).or().like(Drawing::getDrawingFilePath, query.getKeyword()).or()
                              .like(Drawing::getProcInstId, query.getKeyword()).or().like(Drawing::getQcRating, query.getKeyword()));
        }
        return baseMapper.selectPage(page, wrapper);
    }
}