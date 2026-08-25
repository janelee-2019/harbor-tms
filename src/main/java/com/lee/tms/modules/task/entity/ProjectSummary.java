package com.lee.tms.modules.task.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lee.tms.modules.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 项目摘要表
 *
 * @author lee
 * @since 2026-08-20
 */
@Data
@EqualsAndHashCode (callSuper = true)
@TableName ("tsk_project_summary")
public class ProjectSummary extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**
     * 内部序号
     */
    @TableField ("sn")
    private String sn;

    /**
     * 服务请求号
     */
    @TableField ("sr_no")
    private String srNo;

    /**
     * DC 序号
     */
    @TableField ("dc_no")
    private String dcNo;

    /**
     * 服务发起公司
     */
    @TableField ("affiliate_id")
    private Long affiliateId;

    /**
     * 服务请求类型
     */
    @TableField ("sr_type")
    private String srType;

    /**
     * 服务请求接收时间
     */
    @TableField ("sr_received_date")
    private LocalDateTime srReceivedDate;

    /**
     * 技术标提交时间
     */
    @TableField ("tp_submitted_date")
    private LocalDateTime tpSubmittedDate;

    /**
     * 技术标批准时间
     */
    @TableField ("tp_approved_date")
    private LocalDateTime tpApprovedDate;

    /**
     * 商务标提交时间
     */
    @TableField ("cp_submitted_date")
    private LocalDateTime cpSubmittedDate;

    /**
     * 商务标批准时间
     */
    @TableField ("cp_approved_date")
    private LocalDateTime cpApprovedDate;

    /**
     * 成果物提交时间
     */
    @TableField ("amcc_submitted_date")
    private LocalDateTime amccSubmittedDate;

    /**
     * 成果物批准时间
     */
    @TableField ("amcc_approved_date")
    private LocalDateTime amccApprovedDate;

    /**
     * 服务请求开始时间
     */
    @TableField ("sr_start_date")
    private LocalDateTime srStartDate;

    /**
     * 服务请求目标完成时间
     */
    @TableField ("sr_target_date")
    private LocalDateTime srTargetDate;

    /**
     * 服务请求完成时间
     */
    @TableField ("sr_completed_date")
    private LocalDateTime srCompletedDate;

    /**
     * 服务请求计划周期（周）
     */
    @TableField ("sr_plan_weeks")
    private Long srPlanWeeks;

    /**
     * 任务状态
     */
    @TableField ("task_status")
    private String taskStatus;

    /**
     * 是否需要监工
     */
    @TableField ("site_visit")
    private String siteVisit;

    /**
     * 状态
     */
    @TableField ("status")
    private String status;

    /**
     * 服务请求方当前状态
     */
    @TableField ("epm_current_status")
    private String epmCurrentStatus;

    /**
     * 批准工时
     */
    @TableField ("approved_mh")
    private BigDecimal approvedMh;

    /**
     * 已消耗工时
     */
    @TableField ("consumed_mh")
    private BigDecimal consumedMh;

    /**
     * 剩余工时
     */
    @TableField ("remaining_mh")
    private BigDecimal remainingMh;

    /**
     * 任务执行进度
     */
    @TableField ("task_progress")
    private BigDecimal taskProgress;

    /**
     * 预算
     */
    @TableField ("budget_awarded")
    private BigDecimal budgetAwarded;

    /**
     * 备注
     */
    @TableField ("remark")
    private String remark;

    /**
     * 是否已分包
     */
    @TableField ("is_distributed")
    private String isDistributed;

    @TableField ("type")
    private String type;

    @TableField ("project_title")
    private String projectTitle;

    @TableField ("po_no")
    private String poNo;

    @TableField ("sps_manager")
    private String spsManager;

    @TableField ("last_submit_date")
    private LocalDateTime lastSubmitDate;

    @TableField ("sr_actual_execution_days")
    private Long srActualExecutionDays;

    @TableField ("after_last_submit_to_sabic_for_approval_day")
    private Long afterLastSubmitToSabicForApprovalDay;

    @TableField ("execution_sr_duration_day")
    private Long executionSrDurationDay;

}
