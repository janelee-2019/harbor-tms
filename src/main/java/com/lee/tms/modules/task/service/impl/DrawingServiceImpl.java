package com.lee.tms.modules.task.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.tms.domain.drawing.DrawingStageConfig;
import com.lee.tms.infrastructure.auth.AuthContext;
import com.lee.tms.infrastructure.auth.AuthUser;
import com.lee.tms.infrastructure.rest.RestCode;
import com.lee.tms.infrastructure.rest.RestException;
import com.lee.tms.modules.system.service.UserService;
import com.lee.tms.modules.task.dto.body.DrawingAddBody;
import com.lee.tms.modules.task.dto.body.DrawingUpdateBody;
import com.lee.tms.modules.task.dto.query.DrawingPageQuery;
import com.lee.tms.modules.task.dto.body.DrawingTaskCancelBody;
import com.lee.tms.modules.task.dto.body.DrawingTaskIssueBody;
import com.lee.tms.modules.task.dto.body.DrawingTaskSubmitPassedBody;
import com.lee.tms.modules.task.dto.body.DrawingTaskTransferBody;
import com.lee.tms.modules.task.entity.Drawing;
import com.lee.tms.modules.task.mapper.DrawingMapper;
import com.lee.tms.modules.task.service.DrawingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DrawingServiceImpl extends ServiceImpl<DrawingMapper, Drawing> implements DrawingService {

    private static final String DRAWING_CANCELLED_STATUS = "drawing_cancelled";

    @Lazy
    @Autowired
    private UserService userService;

    // ==================== 1. 任务下发 (Issue) ====================

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void issueDraftTask(DrawingTaskIssueBody body) {
        executeIssueTask(body, DrawingStageConfig.DRAFT);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void issueProofreadTask(DrawingTaskIssueBody body) {
        executeIssueTask(body, DrawingStageConfig.PROOFREAD);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void issueQcTask(DrawingTaskIssueBody body) {
        executeIssueTask(body, DrawingStageConfig.QC);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void issueSqcTask(DrawingTaskIssueBody body) {
        executeIssueTask(body, DrawingStageConfig.SQC);
    }

    // ==================== 2. 任务接收 (Receive) ====================

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void receiveDraftTask(List<Long> drawingIds) {
        executeReceiveTask(drawingIds, DrawingStageConfig.DRAFT);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void receiveProofreadTask(List<Long> drawingIds) {
        executeReceiveTask(drawingIds, DrawingStageConfig.PROOFREAD);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void receiveQcTask(List<Long> drawingIds) {
        executeReceiveTask(drawingIds, DrawingStageConfig.QC);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void receiveSqcTask(List<Long> drawingIds) {
        executeReceiveTask(drawingIds, DrawingStageConfig.SQC);
    }

    // ==================== 3. 任务提交 (Submit) ====================

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void submitDraftTask(List<Long> drawingIds) {
        executeSubmitTask(drawingIds, true, DrawingStageConfig.DRAFT);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void submitProofreadTask(DrawingTaskSubmitPassedBody body) {
        executeSubmitTask(body.getIds(), body.getIsPassed(), DrawingStageConfig.PROOFREAD);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void submitQcTask(DrawingTaskSubmitPassedBody body) {
        executeSubmitTask(body.getIds(), body.getIsPassed(), DrawingStageConfig.QC);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void submitSqcTask(DrawingTaskSubmitPassedBody body) {
        executeSubmitTask(body.getIds(), body.getIsPassed(), DrawingStageConfig.SQC);
    }

    // ==================== 业务抽象核心实现 ====================

    /**
     * 规则3：独立下发，不限制下发顺序
     */
    private void executeIssueTask(DrawingTaskIssueBody body, DrawingStageConfig config) {
        validateUserExists(body.getUserId(), config.getRoleName());

        executeBatchTask(body.getIds(), (drawing, authUser, now) -> {
            drawing.setDrawingStatus(config.status(DrawingStageConfig.ISSUED));
            config.getAssigneeSetter().accept(drawing, body.getUserId());
            config.getIssuerSetter().accept(drawing, authUser.getId());
            config.getIssuedDateSetter().accept(drawing, now);
        });
    }

    /**
     * 规则3：接收必须遵循顺序，上一节点未提交不允许接收
     */
    private void executeReceiveTask(List<Long> drawingIds, DrawingStageConfig config) {
        executeBatchTask(drawingIds, (drawing, authUser, now) -> {
            if (config.getIssuedDateGetter().apply(drawing) == null) {
                throw new RestException("图纸 [{}] 节点 [{}] 任务尚未下发，不能接收", drawing.getDrawingNo(), config.getRoleName());
            }

            DrawingStageConfig prevStage = config.getPreviousStage();
            if (prevStage != null && prevStage.getSubmittedDateGetter().apply(drawing) == null) {
                throw new RestException("图纸 [{}] 上一节点 [{}] 尚未提交，不能接收当前任务", drawing.getDrawingNo(), prevStage.getRoleName());
            }

            verifyOpPemission(config.getAssigneeGetter().apply(drawing));

            drawing.setDrawingStatus(config.status(DrawingStageConfig.RECEIVED));
            config.getReceivedDateSetter().accept(drawing, now);
        });
    }

    /**
     * 规则2/规则3：提交前必须处于接收状态（receivedDate != null）
     */
    private void executeSubmitTask(List<Long> drawingIds, Boolean isPassed, DrawingStageConfig config) {
        executeBatchTask(drawingIds, (drawing, authUser, now) -> {
            if (config.getReceivedDateGetter().apply(drawing) == null) {
                throw new RestException("图纸 [{}] 节点 [{}] 任务尚未接收（或转移后未重新接收），不能提交", drawing.getDrawingNo(), config.getRoleName());
            }

            verifyOpPemission(config.getAssigneeGetter().apply(drawing));

            drawing.setDrawingStatus(config.status(DrawingStageConfig.SUBMITTED));
            if (Boolean.TRUE.equals(isPassed)) {
                config.getSubmittedDateSetter().accept(drawing, now);
            }
        });
    }

    // ==================== 底层批处理模板 ====================

    @FunctionalInterface
    private interface DrawingProcessor {
        void process(Drawing drawing, AuthUser authUser, LocalDateTime now);
    }

    private void executeBatchTask(List<Long> ids, DrawingProcessor processor) {
        if (CollUtil.isEmpty(ids)) {
            return;
        }
        List<Drawing> drawings = listByIds(ids);
        if (CollUtil.isEmpty(drawings)) {
            return;
        }

        AuthUser authUser = AuthContext.getAuthUser();
        LocalDateTime now = LocalDateTime.now();

        for (Drawing drawing : drawings) {
            processor.process(drawing, authUser, now);
            if (authUser != null) {
                drawing.setUpdateBy(authUser.getId());
            }
        }
        updateBatchById(drawings);
    }

    private void validateUserExists(Long userId, String roleName) {
        if (userId != null && userService.getUserInfo(userId) == null) {
            throw new RestException("{}不存在 [ID = {}]", roleName, userId);
        }
    }

    private void verifyOpPemission(Long opUserId) {
        AuthUser authUser = AuthContext.getAuthUser();
        if (authUser != null && authUser.notLeader() && !authUser.getId().equals(opUserId)) {
            throw new RestException("只能操作自己的任务");
        }
    }

    // ==================== CRUD & Page ====================

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertDrawing(DrawingAddBody body) {
        Drawing entity = new Drawing();
        BeanUtil.copyProperties(body, entity);
        entity.setCreateBy(AuthContext.getCurrentUserId());
        save(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateDrawing(DrawingUpdateBody body) {
        Drawing entity = getDetail(body.getId());
        BeanUtil.copyProperties(body, entity);
        entity.setUpdateBy(AuthContext.getCurrentUserId());
        updateById(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteDrawing(List<Long> ids) {
        removeBatchByIds(ids);
    }

    @Override
    public Drawing getDetail(Long id) {
        Drawing entity = getById(id);
        if (entity == null) {
            throw new RestException(RestCode.SYS_ERR, "图纸信息表不存在 [ID = {}]", id);
        }
        return entity;
    }

    @Override
    public IPage<Drawing> getPage(DrawingPageQuery query) {
        IPage<Drawing> page = new Page<>(query.getPageNo(), query.getPageSize());
        LambdaQueryWrapper<Drawing> wrapper = new LambdaQueryWrapper<>();

        if (StrUtil.isNotBlank(query.getKeyword())) {
            wrapper.and(w -> w.like(Drawing::getSn, query.getKeyword())
                              .or().like(Drawing::getDrawingNo, query.getKeyword())
                              .or().like(Drawing::getDrawingStatus, query.getKeyword()));
        }
        return baseMapper.selectPage(page, wrapper);
    }
}