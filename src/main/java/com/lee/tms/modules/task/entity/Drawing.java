package com.lee.tms.modules.task.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lee.tms.modules.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 图纸信息表
 *
 * @author lee
 * @since 2026-08-20
 */
@Data
@EqualsAndHashCode (callSuper = true)
@TableName ("tsk_drawing")
public class Drawing extends BaseEntity
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
     * 批次 ID
     */
    @TableField ("batch_id")
    private Long batchId;

    /**
     * 批次号
     */
    @TableField ("batch_no")
    private String batchNo;

    /**
     * 图纸内部 ID
     */
    @TableField ("drawing_id")
    private Long drawingId;

    /**
     * 图纸文件名
     */
    @TableField ("drawing_file_name")
    private String drawingFileName;

    /**
     * 图号
     */
    @TableField ("drawing_no")
    private String drawingNo;

    /**
     * 图纸所在文件夹
     */
    @TableField ("drawing_file_dir")
    private String drawingFileDir;

    /**
     * 图纸范畴
     */
    @TableField ("drawing_category")
    private String drawingCategory;

    /**
     * 图纸类型
     */
    @TableField ("drawing_type")
    private String drawingType;

    /**
     * 绘图工时
     */
    @TableField ("draft_mh")
    private BigDecimal draftMh;

    /**
     * 绘图员 ID
     */
    @TableField ("drafter_id")
    private Long drafterId;

    /**
     * 校对工时
     */
    @TableField ("proofread_mh")
    private BigDecimal proofreadMh;

    /**
     * 校对员 ID
     */
    @TableField ("proofreader_id")
    private Long proofreaderId;

    /**
     * 初级质控员 ID
     */
    @TableField ("qc_id")
    private Long qcId;

    /**
     * 高级质控员 ID
     */
    @TableField ("sqc_id")
    private Long sqcId;

    /**
     * 图纸状态
     */
    @TableField ("drawing_status")
    private String drawingStatus;

    /**
     * 备注
     */
    @TableField ("remark")
    private String remark;

    /**
     * DC 序号
     */
    @TableField ("dc_no")
    private String dcNo;

    /**
     * 图纸文件绝对路径
     */
    @TableField ("drawing_file_path")
    private String drawingFilePath;

    /**
     * 流程 ID
     */
    @TableField ("proc_inst_id")
    private String procInstId;

    /**
     * 绘图接收时间
     */
    @TableField ("draft_received_date")
    private LocalDateTime draftReceivedDate;

    /**
     * 校对接收时间
     */
    @TableField ("proofread_received_date")
    private LocalDateTime proofreadReceivedDate;

    /**
     * QC 接收时间
     */
    @TableField ("qc_received_date")
    private LocalDateTime qcReceivedDate;

    /**
     * SQC 接收时间
     */
    @TableField ("sqc_received_date")
    private LocalDateTime sqcReceivedDate;

    /**
     * 绘图下发时间
     */
    @TableField ("draft_issued_date")
    private LocalDateTime draftIssuedDate;

    /**
     * 校对下发时间
     */
    @TableField ("proofread_issued_date")
    private LocalDateTime proofreadIssuedDate;

    /**
     * QC 下发时间
     */
    @TableField ("qc_issued_date")
    private LocalDateTime qcIssuedDate;

    /**
     * SQC 下发时间
     */
    @TableField ("sqc_issued_date")
    private LocalDateTime sqcIssuedDate;

    /**
     * 绘图下发者
     */
    @TableField ("draft_issuer_id")
    private Long draftIssuerId;

    /**
     * 校对下发者
     */
    @TableField ("proofread_issuer_id")
    private Long proofreadIssuerId;

    /**
     * QC 下发者
     */
    @TableField ("qc_issuer_id")
    private Long qcIssuerId;

    /**
     * SQC 下发者
     */
    @TableField ("sqc_issuer_id")
    private Long sqcIssuerId;

    /**
     * 绘图提交时间
     */
    @TableField ("draft_submitted_date")
    private LocalDateTime draftSubmittedDate;

    /**
     * 校对提交时间
     */
    @TableField ("proofread_submitted_date")
    private LocalDateTime proofreadSubmittedDate;

    /**
     * QC 提交时间
     */
    @TableField ("qc_submitted_date")
    private LocalDateTime qcSubmittedDate;

    /**
     * SQC 提交时间
     */
    @TableField ("sqc_submitted_date")
    private LocalDateTime sqcSubmittedDate;

    /**
     * 图纸任务截止日期
     */
    @TableField ("task_due_date")
    private LocalDateTime taskDueDate;

    /**
     * QC 评分
     */
    @TableField ("qc_rating")
    private String qcRating;

    /**
     * QC 评分时间
     */
    @TableField ("qc_rating_date")
    private LocalDateTime qcRatingDate;

}
