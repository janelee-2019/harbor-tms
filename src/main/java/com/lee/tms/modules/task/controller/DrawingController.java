package com.lee.tms.modules.task.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.tms.infrastructure.log.ApiLog;
import com.lee.tms.infrastructure.rest.R;
import com.lee.tms.infrastructure.rest.body.DefaultOpByIdsBody;
import com.lee.tms.infrastructure.rest.query.DefaultInfoQuery;
import com.lee.tms.infrastructure.rest.vo.DefaultPageVo;
import com.lee.tms.modules.task.dto.body.*;
import com.lee.tms.modules.task.dto.query.DrawingPageQuery;
import com.lee.tms.modules.task.dto.vo.*;
import com.lee.tms.modules.task.entity.Drawing;
import com.lee.tms.modules.task.service.DrawingService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/**
 * 图纸管理
 *
 * @author lee
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/task/drawing")
public class DrawingController
{
    private final DrawingService drawingService;

    /* ==================== 基础 CRUD ==================== */

    /**
     * 添加图纸
     */
    @RequiresPermissions("task:drawing:add")
    @ApiLog(module = "", tag = "新增Drawing")
    @PostMapping("add")
    public R insertDrawing(@Validated @RequestBody DrawingAddBody body)
    {
        drawingService.insertDrawing(body);
        return R.ok();
    }

    /**
     * 更新图纸
     */
    @RequiresPermissions("task:drawing:update")
    @ApiLog(module = "", tag = "更新Drawing")
    @PostMapping("update")
    public R updateDrawing(@Validated @RequestBody DrawingUpdateBody body)
    {
        drawingService.updateDrawing(body);
        return R.ok();
    }

    /**
     * 删除图纸
     */
    @RequiresPermissions("task:drawing:delete")
    @ApiLog(module = "", tag = "删除Drawing")
    @PostMapping("delete")
    public R deleteDrawing(@Validated @RequestBody DefaultOpByIdsBody body)
    {
        drawingService.deleteDrawing(body.getIds());
        return R.ok();
    }

    /**
     * 获取图纸详情
     */
    @RequiresPermissions("task:drawing:info")
    @GetMapping("info")
    public R<DrawingVo> getDrawingInfo(@Validated DefaultInfoQuery query)
    {
        return R.ok(DrawingVo.fromModel(drawingService.getDetail(query.getId())));
    }

    /**
     * 获取图纸分页列表
     */
    @RequiresPermissions("task:drawing:page")
    @GetMapping("page")
    public R<DefaultPageVo<DrawingListItemVo>> getDrawingsPage(@Validated DrawingPageQuery query)
    {
        IPage<Drawing> page = drawingService.getPage(query);
        DefaultPageVo<DrawingListItemVo> vos = new DefaultPageVo<>(query.getPageNo(), query.getPageSize(), page.getTotal(),
                                                                   page.getRecords().stream().map(DrawingListItemVo::fromModel).collect(Collectors.toList()));
        return R.ok(vos);
    }

    /* ==================== 任务下发 ==================== */

    /**
     * 下发绘图任务
     */
    @RequiresPermissions("task:drawing:issue_draft")
    @ApiLog(module = "", tag = "下发绘图任务")
    @PostMapping("issueDraftTask")
    public R issueDraftTask(@Validated @RequestBody DrawingTaskIssueBody body)
    {
        drawingService.issueDraftTask(body);
        return R.ok();
    }

    /**
     * 下发校对任务
     */
    @RequiresPermissions("task:drawing:issue_proofread")
    @ApiLog(module = "", tag = "下发校对任务")
    @PostMapping("issueProofreadTask")
    public R issueProofreadTask(@Validated @RequestBody DrawingTaskIssueBody body)
    {
        drawingService.issueProofreadTask(body);
        return R.ok();
    }

    /**
     * 下发初级质检任务
     */
    @RequiresPermissions("task:drawing:issue_qc")
    @ApiLog(module = "", tag = "下发初级质检任务")
    @PostMapping("issueQcTask")
    public R issueQcTask(@Validated @RequestBody DrawingTaskIssueBody body)
    {
        drawingService.issueQcTask(body);
        return R.ok();
    }

    /**
     * 下发高级质检任务
     */
    @RequiresPermissions("task:drawing:issue_sqc")
    @ApiLog(module = "", tag = "下发高级质检任务")
    @PostMapping("issueSqcTask")
    public R issueSqcTask(@Validated @RequestBody DrawingTaskIssueBody body)
    {
        drawingService.issueSqcTask(body);
        return R.ok();
    }

    /* ==================== 任务接收 ==================== */

    /**
     * 接收绘图任务
     */
    @RequiresPermissions("task:drawing:receive_draft")
    @ApiLog(module = "", tag = "接收绘图任务")
    @PostMapping("receiveDraftTask")
    public R receiveDraftTask(@Validated @RequestBody DefaultOpByIdsBody body)
    {
        drawingService.receiveDraftTask(body.getIds());
        return R.ok();
    }

    /**
     * 接收校对任务
     */
    @RequiresPermissions("task:drawing:receive_proofread")
    @ApiLog(module = "", tag = "接收校对任务")
    @PostMapping("receiveProofreadTask")
    public R receiveProofreadTask(@Validated @RequestBody DefaultOpByIdsBody body)
    {
        drawingService.receiveProofreadTask(body.getIds());
        return R.ok();
    }

    /**
     * 接收初级质检任务
     */
    @RequiresPermissions("task:drawing:receive_qc")
    @ApiLog(module = "", tag = "接收初级质检任务")
    @PostMapping("receiveQcTask")
    public R receiveQcTask(@Validated @RequestBody DefaultOpByIdsBody body)
    {
        drawingService.receiveQcTask(body.getIds());
        return R.ok();
    }

    /**
     * 接收高级质检任务
     */
    @RequiresPermissions("task:drawing:receive_sqc")
    @ApiLog(module = "", tag = "接收高级质检任务")
    @PostMapping("receiveSqcTask")
    public R receiveSqcTask(@Validated @RequestBody DefaultOpByIdsBody body)
    {
        drawingService.receiveSqcTask(body.getIds());
        return R.ok();
    }

    /* ==================== 任务转办 ==================== */

    /**
     * 转办绘图任务
     */
    @RequiresPermissions("task:drawing:transfer_draft")
    @ApiLog(module = "", tag = "转办绘图任务")
    @PostMapping("transferDraftTask")
    public R transferDraftTask(@Validated @RequestBody DrawingTaskTransferBody body)
    {
        drawingService.transferDraftTask(body);
        return R.ok();
    }

    /**
     * 转办校对任务
     */
    @RequiresPermissions("task:drawing:transfer_proofread")
    @ApiLog(module = "", tag = "转办校对任务")
    @PostMapping("transferProofreadTask")
    public R transferProofreadTask(@Validated @RequestBody DrawingTaskTransferBody body)
    {
        drawingService.transferProofreadTask(body);
        return R.ok();
    }

    /**
     * 转办初级质检任务
     */
    @RequiresPermissions("task:drawing:transfer_qc")
    @ApiLog(module = "", tag = "转办初级质检任务")
    @PostMapping("transferQcTask")
    public R transferQcTask(@Validated @RequestBody DrawingTaskTransferBody body)
    {
        drawingService.transferQcTask(body);
        return R.ok();
    }

    /**
     * 转办高级质检任务
     */
    @RequiresPermissions("task:drawing:transfer_sqc")
    @ApiLog(module = "", tag = "转办高级质检任务")
    @PostMapping("transferSqcTask")
    public R transferSqcTask(@Validated @RequestBody DrawingTaskTransferBody body)
    {
        drawingService.transferSqcTask(body);
        return R.ok();
    }

    /* ==================== 任务提交 ==================== */

    /**
     * 提交绘图任务
     */
    @RequiresPermissions("task:drawing:submit_draft")
    @ApiLog(module = "", tag = "提交绘图任务")
    @PostMapping("submitDraftTask")
    public R submitDraftTask(@Validated @RequestBody DefaultOpByIdsBody body)
    {
        drawingService.submitDraftTask(body.getIds());
        return R.ok();
    }

    /**
     * 提交校对任务
     */
    @RequiresPermissions("task:drawing:submit_proofread")
    @ApiLog(module = "", tag = "提交校对任务")
    @PostMapping("submitProofreadTask")
    public R submitProofreadTask(@Validated @RequestBody DrawingTaskSubmitPassedBody body)
    {
        drawingService.submitProofreadTask(body);
        return R.ok();
    }

    /**
     * 提交初级质检任务
     */
    @RequiresPermissions("task:drawing:submit_qc")
    @ApiLog(module = "", tag = "提交初级质检任务")
    @PostMapping("submitQcTask")
    public R submitQcTask(@Validated @RequestBody DrawingTaskSubmitPassedBody body)
    {
        drawingService.submitQcTask(body);
        return R.ok();
    }

    /**
     * 提交高级质检任务
     */
    @RequiresPermissions("task:drawing:submit_sqc")
    @ApiLog(module = "", tag = "提交高级质检任务")
    @PostMapping("submitSqcTask")
    public R submitSqcTask(@Validated @RequestBody DrawingTaskSubmitPassedBody body)
    {
        drawingService.submitSqcTask(body);
        return R.ok();
    }

    /* ==================== 任务取消提交 ==================== */

    /**
     * 取消提交绘图任务
     */
    @RequiresPermissions("task:drawing:cancel_submit_draft")
    @ApiLog(module = "", tag = "取消提交绘图任务")
    @PostMapping("cancelSubmitDraftTask")
    public R cancelSubmitDraftTask(@Validated @RequestBody DefaultOpByIdsBody body)
    {
        drawingService.cancelSubmitDraftTask(body.getIds());
        return R.ok();
    }

    /**
     * 取消提交校对任务
     */
    @RequiresPermissions("task:drawing:cancel_submit_proofread")
    @ApiLog(module = "", tag = "取消提交校对任务")
    @PostMapping("cancelSubmitProofreadTask")
    public R cancelSubmitProofreadTask(@Validated @RequestBody DefaultOpByIdsBody body)
    {
        drawingService.cancelSubmitProofreadTask(body.getIds());
        return R.ok();
    }

    /**
     * 取消提交初级质检任务
     */
    @RequiresPermissions("task:drawing:cancel_submit_qc")
    @ApiLog(module = "", tag = "取消提交初级质检任务")
    @PostMapping("cancelSubmitQcTask")
    public R cancelSubmitQcTask(@Validated @RequestBody DefaultOpByIdsBody body)
    {
        drawingService.cancelSubmitQcTask(body.getIds());
        return R.ok();
    }

    /**
     * 取消提交高级质检任务
     */
    @RequiresPermissions("task:drawing:cancel_submit_sqc")
    @ApiLog(module = "", tag = "取消提交高级质检任务")
    @PostMapping("cancelSubmitSqcTask")
    public R cancelSubmitSqcTask(@Validated @RequestBody DefaultOpByIdsBody body)
    {
        drawingService.cancelSubmitSqcTask(body.getIds());
        return R.ok();
    }

    /* ==================== 任务取消 ==================== */

    /**
     * 取消图纸任务
     */
    @RequiresPermissions("task:drawing:cancel")
    @ApiLog(module = "", tag = "取消图纸任务")
    @PostMapping("cancelDrawingTask")
    public R cancelDrawingTask(@Validated @RequestBody DrawingTaskCancelBody body)
    {
        drawingService.cancelDrawingTask(body);
        return R.ok();
    }
}