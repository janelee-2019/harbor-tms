package com.lee.tms.modules.task.dto.query;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class DrawingQuery
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
     * 批次 ID
     */
    private Long batchId;
    /**
     * 批次号
     */
    private String batchNo;
    /**
     * 图纸内部 ID
     */
    private Long drawingId;
    /**
     * 图纸文件名
     */
    private String drawingFileName;
    /**
     * 图号
     */
    private String drawingNo;
    /**
     * 图纸所在文件夹
     */
    private String drawingFileDir;
    /**
     * 图纸范畴
     */
    private String drawingCategory;
    /**
     * 图纸类型
     */
    private String drawingType;
    /**
     * 绘图工时
     */
    private BigDecimal draftMh;
    /**
     * 绘图员 ID
     */
    private Long drafterId;
    /**
     * 校对工时
     */
    private BigDecimal proofreadMh;
    /**
     * 校对员 ID
     */
    private Long proofreaderId;
    /**
     * 初级质控员 ID
     */
    private Long qcId;
    /**
     * 高级质控员 ID
     */
    private Long sqcId;
    /**
     * 图纸状态
     */
    private String drawingStatus;
    /**
     * 备注
     */
    private String remark;
    /**
     * DC 序号
     */
    private String dcNo;
    /**
     * 图纸文件绝对路径
     */
    private String drawingFilePath;
    /**
     * 流程 ID
     */
    private String procInstId;
    /**
     * 绘图接收时间
     */
    private String draftReceivedDate;
    /**
     * 校对接收时间
     */
    private String proofreadReceivedDate;
    /**
     * QC 接收时间
     */
    private String qcReceivedDate;
    /**
     * SQC 接收时间
     */
    private String sqcReceivedDate;
    /**
     * 绘图下发时间
     */
    private String draftIssuedDate;
    /**
     * 校对下发时间
     */
    private String proofreadIssuedDate;
    /**
     * QC 下发时间
     */
    private String qcIssuedDate;
    /**
     * SQC 下发时间
     */
    private String sqcIssuedDate;
    /**
     * 绘图下发者
     */
    private Long draftIssuerId;
    /**
     * 校对下发者
     */
    private Long proofreadIssuerId;
    /**
     * QC 下发者
     */
    private Long qcIssuerId;
    /**
     * SQC 下发者
     */
    private Long sqcIssuerId;
    /**
     * 绘图提交时间
     */
    private String draftSubmittedDate;
    /**
     * 校对提交时间
     */
    private String proofreadSubmittedDate;
    /**
     * QC 提交时间
     */
    private String qcSubmittedDate;
    /**
     * SQC 提交时间
     */
    private String sqcSubmittedDate;
    /**
     * 图纸任务截止日期
     */
    private String taskDueDate;
    /**
     * QC 评分
     */
    private String qcRating;
    /**
     * QC 评分时间
     */
    private String qcRatingDate;
    /**
     * 查询关键字，支持产品名称、产品简称和产品代码模糊查询
     */
    private String keyword;

    public LocalDateTime getDraftReceivedDate()
    {
        if (StrUtil.isNotBlank(draftReceivedDate))
        {
            return LocalDateTimeUtil.parse(draftReceivedDate, "yyyy-MM-dd");
        }
        return null;
    }

    public LocalDateTime getProofreadReceivedDate()
    {
        if (StrUtil.isNotBlank(proofreadReceivedDate))
        {
            return LocalDateTimeUtil.parse(proofreadReceivedDate, "yyyy-MM-dd");
        }
        return null;
    }

    public LocalDateTime getQcReceivedDate()
    {
        if (StrUtil.isNotBlank(qcReceivedDate))
        {
            return LocalDateTimeUtil.parse(qcReceivedDate, "yyyy-MM-dd");
        }
        return null;
    }

    public LocalDateTime getSqcReceivedDate()
    {
        if (StrUtil.isNotBlank(sqcReceivedDate))
        {
            return LocalDateTimeUtil.parse(sqcReceivedDate, "yyyy-MM-dd");
        }
        return null;
    }

    public LocalDateTime getDraftIssuedDate()
    {
        if (StrUtil.isNotBlank(draftIssuedDate))
        {
            return LocalDateTimeUtil.parse(draftIssuedDate, "yyyy-MM-dd");
        }
        return null;
    }

    public LocalDateTime getProofreadIssuedDate()
    {
        if (StrUtil.isNotBlank(proofreadIssuedDate))
        {
            return LocalDateTimeUtil.parse(proofreadIssuedDate, "yyyy-MM-dd");
        }
        return null;
    }

    public LocalDateTime getQcIssuedDate()
    {
        if (StrUtil.isNotBlank(qcIssuedDate))
        {
            return LocalDateTimeUtil.parse(qcIssuedDate, "yyyy-MM-dd");
        }
        return null;
    }

    public LocalDateTime getSqcIssuedDate()
    {
        if (StrUtil.isNotBlank(sqcIssuedDate))
        {
            return LocalDateTimeUtil.parse(sqcIssuedDate, "yyyy-MM-dd");
        }
        return null;
    }

    public LocalDateTime getDraftSubmittedDate()
    {
        if (StrUtil.isNotBlank(draftSubmittedDate))
        {
            return LocalDateTimeUtil.parse(draftSubmittedDate, "yyyy-MM-dd");
        }
        return null;
    }

    public LocalDateTime getProofreadSubmittedDate()
    {
        if (StrUtil.isNotBlank(proofreadSubmittedDate))
        {
            return LocalDateTimeUtil.parse(proofreadSubmittedDate, "yyyy-MM-dd");
        }
        return null;
    }

    public LocalDateTime getQcSubmittedDate()
    {
        if (StrUtil.isNotBlank(qcSubmittedDate))
        {
            return LocalDateTimeUtil.parse(qcSubmittedDate, "yyyy-MM-dd");
        }
        return null;
    }

    public LocalDateTime getSqcSubmittedDate()
    {
        if (StrUtil.isNotBlank(sqcSubmittedDate))
        {
            return LocalDateTimeUtil.parse(sqcSubmittedDate, "yyyy-MM-dd");
        }
        return null;
    }

    public LocalDateTime getTaskDueDate()
    {
        if (StrUtil.isNotBlank(taskDueDate))
        {
            return LocalDateTimeUtil.parse(taskDueDate, "yyyy-MM-dd");
        }
        return null;
    }

    public LocalDateTime getQcRatingDate()
    {
        if (StrUtil.isNotBlank(qcRatingDate))
        {
            return LocalDateTimeUtil.parse(qcRatingDate, "yyyy-MM-dd");
        }
        return null;
    }
}