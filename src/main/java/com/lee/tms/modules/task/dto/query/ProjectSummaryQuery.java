package com.lee.tms.modules.task.dto.query;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProjectSummaryQuery
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
    private String srReceivedDate;
    /**
     * 技术标提交时间
     */
    private String tpSubmittedDate;
    /**
     * 技术标批准时间
     */
    private String tpApprovedDate;
    /**
     * 商务标提交时间
     */
    private String cpSubmittedDate;
    /**
     * 商务标批准时间
     */
    private String cpApprovedDate;
    /**
     * 成果物提交时间
     */
    private String amccSubmittedDate;
    /**
     * 成果物批准时间
     */
    private String amccApprovedDate;
    /**
     * 服务请求开始时间
     */
    private String srStartDate;
    /**
     * 服务请求目标完成时间
     */
    private String srTargetDate;
    /**
     * 服务请求完成时间
     */
    private String srCompletedDate;
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
    private String type;
    private String projectTitle;
    private String poNo;
    private String spsManager;
    private String lastSubmitDate;
    private Long srActualExecutionDays;
    private Long afterLastSubmitToSabicForApprovalDay;
    private Long executionSrDurationDay;
    /**
     * 查询关键字，支持产品名称、产品简称和产品代码模糊查询
     */
    private String keyword;

    public LocalDateTime getSrReceivedDate()
    {
        if (StrUtil.isNotBlank(srReceivedDate))
        {
            return LocalDateTimeUtil.parse(srReceivedDate, "yyyy-MM-dd");
        }
        return null;
    }

    public LocalDateTime getTpSubmittedDate()
    {
        if (StrUtil.isNotBlank(tpSubmittedDate))
        {
            return LocalDateTimeUtil.parse(tpSubmittedDate, "yyyy-MM-dd");
        }
        return null;
    }

    public LocalDateTime getTpApprovedDate()
    {
        if (StrUtil.isNotBlank(tpApprovedDate))
        {
            return LocalDateTimeUtil.parse(tpApprovedDate, "yyyy-MM-dd");
        }
        return null;
    }

    public LocalDateTime getCpSubmittedDate()
    {
        if (StrUtil.isNotBlank(cpSubmittedDate))
        {
            return LocalDateTimeUtil.parse(cpSubmittedDate, "yyyy-MM-dd");
        }
        return null;
    }

    public LocalDateTime getCpApprovedDate()
    {
        if (StrUtil.isNotBlank(cpApprovedDate))
        {
            return LocalDateTimeUtil.parse(cpApprovedDate, "yyyy-MM-dd");
        }
        return null;
    }

    public LocalDateTime getAmccSubmittedDate()
    {
        if (StrUtil.isNotBlank(amccSubmittedDate))
        {
            return LocalDateTimeUtil.parse(amccSubmittedDate, "yyyy-MM-dd");
        }
        return null;
    }

    public LocalDateTime getAmccApprovedDate()
    {
        if (StrUtil.isNotBlank(amccApprovedDate))
        {
            return LocalDateTimeUtil.parse(amccApprovedDate, "yyyy-MM-dd");
        }
        return null;
    }

    public LocalDateTime getSrStartDate()
    {
        if (StrUtil.isNotBlank(srStartDate))
        {
            return LocalDateTimeUtil.parse(srStartDate, "yyyy-MM-dd");
        }
        return null;
    }

    public LocalDateTime getSrTargetDate()
    {
        if (StrUtil.isNotBlank(srTargetDate))
        {
            return LocalDateTimeUtil.parse(srTargetDate, "yyyy-MM-dd");
        }
        return null;
    }

    public LocalDateTime getSrCompletedDate()
    {
        if (StrUtil.isNotBlank(srCompletedDate))
        {
            return LocalDateTimeUtil.parse(srCompletedDate, "yyyy-MM-dd");
        }
        return null;
    }

    public LocalDateTime getLastSubmitDate()
    {
        if (StrUtil.isNotBlank(lastSubmitDate))
        {
            return LocalDateTimeUtil.parse(lastSubmitDate, "yyyy-MM-dd");
        }
        return null;
    }
}