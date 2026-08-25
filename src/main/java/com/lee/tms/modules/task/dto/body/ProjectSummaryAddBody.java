package com.lee.tms.modules.task.dto.body;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProjectSummaryAddBody
{
    /**
     * 内部序号
     */
    private String sn;
    /**
     * 服务请求号
     */
    private String srNo;
    /**
     * DC 序号
     */
    private String dcNo;
    /**
     * 服务发起公司
     */
    private Long affiliateId;
    /**
     * 服务请求类型
     */
    private String srType;
    /**
     * 服务请求接收时间
     */
    @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime srReceivedDate;
    /**
     * 技术标提交时间
     */
    @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime tpSubmittedDate;
    /**
     * 技术标批准时间
     */
    @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime tpApprovedDate;
    /**
     * 商务标提交时间
     */
    @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime cpSubmittedDate;
    /**
     * 商务标批准时间
     */
    @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime cpApprovedDate;
    /**
     * 成果物提交时间
     */
    @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime amccSubmittedDate;
    /**
     * 成果物批准时间
     */
    @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime amccApprovedDate;
    /**
     * 服务请求开始时间
     */
    @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime srStartDate;
    /**
     * 服务请求目标完成时间
     */
    @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime srTargetDate;
    /**
     * 服务请求完成时间
     */
    @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime srCompletedDate;
    /**
     * 服务请求计划周期（周）
     */
    private Long srPlanWeeks;
    /**
     * 任务状态
     */
    private String taskStatus;
    /**
     * 是否需要监工
     */
    private String siteVisit;
    /**
     * 状态
     */
    private String status;
    /**
     * 服务请求方当前状态
     */
    private String epmCurrentStatus;
    /**
     * 批准工时
     */
    private BigDecimal approvedMh;
    /**
     * 已消耗工时
     */
    private BigDecimal consumedMh;
    /**
     * 剩余工时
     */
    private BigDecimal remainingMh;
    /**
     * 任务执行进度
     */
    private BigDecimal taskProgress;
    /**
     * 预算
     */
    private BigDecimal budgetAwarded;
    /**
     * 备注
     */
    private String remark;
    /**
     * 是否已分包
     */
    private String isDistributed;
    /**
     *
     */
    private String type;
    /**
     *
     */
    private String projectTitle;
    /**
     *
     */
    private String poNo;
    /**
     *
     */
    private String spsManager;
    /**
     *
     */
    @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime lastSubmitDate;
    /**
     *
     */
    private Long srActualExecutionDays;
    /**
     *
     */
    private Long afterLastSubmitToSabicForApprovalDay;
    /**
     *
     */
    private Long executionSrDurationDay;
}