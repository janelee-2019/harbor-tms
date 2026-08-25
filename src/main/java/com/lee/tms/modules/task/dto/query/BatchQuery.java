package com.lee.tms.modules.task.dto.query;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BatchQuery
{
    /**
     * 服务请求 ID
     */
    private Long srId;
    /**
     * 内部序号
     */
    private String sn;
    /**
     * 包 ID
     */
    private Long packageId;
    /**
     * 包序号
     */
    private String packageNo;
    /**
     * 批次号
     */
    private String batchNo;
    /**
     * 小组 ID
     */
    private Long groupId;
    /**
     * 批次中图纸或文档数量
     */
    private Long batchQuantity;
    /**
     * 批次额定工时
     */
    private BigDecimal batchSpecMh;
    /**
     * 批次下发工时
     */
    private BigDecimal batchIssueMh;
    /**
     * 批次目标完成时间
     */
    private String batchTargetDate;
    /**
     * 批次完成时间
     */
    private String batchCompletedDate;
    /**
     * 批次任务开始时间
     */
    private String batchStartDate;
    /**
     * QC 评分
     */
    private String qcRating;
    /**
     * QC 完成时间
     */
    private String qcCompletedDate;
    /**
     * 备注
     */
    private String remark;
    /**
     * 下发时间
     */
    private String issueDate;
    /**
     * 下发者
     */
    private Long issuerId;
    /**
     * 工作日天数
     */
    private Long batchWorkDays;
    /**
     * 质控员
     */
    private Long qcId;
    /**
     * 是否已下发
     */
    private String isIssued;
    /**
     * 是否已分图纸
     */
    private String isDistributed;
    /**
     * 是否已评分
     */
    private String isRated;
    /**
     * 查询关键字，支持产品名称、产品简称和产品代码模糊查询
     */
    private String keyword;

    public LocalDateTime getBatchTargetDate()
    {
        if (StrUtil.isNotBlank(batchTargetDate))
        {
            return LocalDateTimeUtil.parse(batchTargetDate, "yyyy-MM-dd");
        }
        return null;
    }

    public LocalDateTime getBatchCompletedDate()
    {
        if (StrUtil.isNotBlank(batchCompletedDate))
        {
            return LocalDateTimeUtil.parse(batchCompletedDate, "yyyy-MM-dd");
        }
        return null;
    }

    public LocalDateTime getBatchStartDate()
    {
        if (StrUtil.isNotBlank(batchStartDate))
        {
            return LocalDateTimeUtil.parse(batchStartDate, "yyyy-MM-dd");
        }
        return null;
    }

    public LocalDateTime getQcCompletedDate()
    {
        if (StrUtil.isNotBlank(qcCompletedDate))
        {
            return LocalDateTimeUtil.parse(qcCompletedDate, "yyyy-MM-dd");
        }
        return null;
    }

    public LocalDateTime getIssueDate()
    {
        if (StrUtil.isNotBlank(issueDate))
        {
            return LocalDateTimeUtil.parse(issueDate, "yyyy-MM-dd");
        }
        return null;
    }
}