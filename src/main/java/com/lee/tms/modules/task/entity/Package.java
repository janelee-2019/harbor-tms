package com.lee.tms.modules.task.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lee.tms.modules.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 分包信息表
 *
 * @author lee
 * @since 2026-08-20
 */
@Data
@EqualsAndHashCode (callSuper = true)
@TableName ("tsk_package")
public class Package extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**
     * 服务请求 ID
     */
    @TableField ("sr_id")
    private Long srId;

    /**
     * 包序号
     */
    @TableField ("package_no")
    private String packageNo;

    /**
     * 包类型
     */
    @TableField ("package_type")
    private String packageType;

    /**
     * 负责团队 ID
     */
    @TableField ("team_id")
    private Long teamId;

    /**
     * 包中的图纸或文档数量
     */
    @TableField ("package_quantity")
    private Long packageQuantity;

    /**
     * 包工时
     */
    @TableField ("package_mh")
    private BigDecimal packageMh;

    /**
     * 包任务开始时间
     */
    @TableField ("package_start_date")
    private LocalDateTime packageStartDate;

    /**
     * 包任务完成时间
     */
    @TableField ("package_completed_date")
    private LocalDateTime packageCompletedDate;

    /**
     * 工作日天数
     */
    @TableField ("package_work_days")
    private Long packageWorkDays;

    /**
     * 包下发时间
     */
    @TableField ("issue_date")
    private LocalDateTime issueDate;

    /**
     * 下发者
     */
    @TableField ("issuer_id")
    private Long issuerId;

    /**
     * 备注
     */
    @TableField ("remark")
    private String remark;

    /**
     * 内部序号
     */
    @TableField ("sn")
    private String sn;

    /**
     * 包任务目标完成时间
     */
    @TableField ("package_target_date")
    private LocalDateTime packageTargetDate;

    /**
     * 是否已下发
     */
    @TableField ("is_issued")
    private String isIssued;

    /**
     * 是否已分批次
     */
    @TableField ("is_distributed")
    private String isDistributed;

}
