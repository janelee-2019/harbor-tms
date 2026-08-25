package com.lee.tms.modules.task.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lee.tms.modules.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 批次信息表
 *
 * @author lee
 * @since 2026-08-20
 */
@Data
@EqualsAndHashCode (callSuper = true)
@TableName ("tsk_batch")
public class Batch extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**
     * 服务请求 ID
     */
    @TableField ("sr_id")
    private Long srId;

    /**
     * 内部序号
     */
    @TableField ("sn")
    private String sn;

    /**
     * 包 ID
     */
    @TableField ("package_id")
    private Long packageId;

    /**
     * 包序号
     */
    @TableField ("package_no")
    private String packageNo;

    /**
     * 批次号
     */
    @TableField ("batch_no")
    private String batchNo;

    /**
     * 小组 ID
     */
    @TableField ("group_id")
    private Long groupId;

    /**
     * 批次中图纸或文档数量
     */
    @TableField ("batch_quantity")
    private Long batchQuantity;

    /**
     * 批次额定工时
     */
    @TableField ("batch_spec_mh")
    private BigDecimal batchSpecMh;

    /**
     * 批次下发工时
     */
    @TableField ("batch_issue_mh")
    private BigDecimal batchIssueMh;

    /**
     * 批次目标完成时间
     */
    @TableField ("batch_target_date")
    private LocalDateTime batchTargetDate;

    /**
     * 批次完成时间
     */
    @TableField ("batch_completed_date")
    private LocalDateTime batchCompletedDate;

    /**
     * 批次任务开始时间
     */
    @TableField ("batch_start_date")
    private LocalDateTime batchStartDate;

    /**
     * QC 评分
     */
    @TableField ("qc_rating")
    private String qcRating;

    /**
     * QC 完成时间
     */
    @TableField ("qc_completed_date")
    private LocalDateTime qcCompletedDate;

    /**
     * 备注
     */
    @TableField ("remark")
    private String remark;

    /**
     * 下发时间
     */
    @TableField ("issue_date")
    private LocalDateTime issueDate;

    /**
     * 下发者
     */
    @TableField ("issuer_id")
    private Long issuerId;

    /**
     * 工作日天数
     */
    @TableField ("batch_work_days")
    private Long batchWorkDays;

    /**
     * 质控员
     */
    @TableField ("qc_id")
    private Long qcId;

    /**
     * 是否已下发
     */
    @TableField ("is_issued")
    private String isIssued;

    /**
     * 是否已分图纸
     */
    @TableField ("is_distributed")
    private String isDistributed;

    /**
     * 是否已评分
     */
    @TableField ("is_rated")
    private String isRated;

}
