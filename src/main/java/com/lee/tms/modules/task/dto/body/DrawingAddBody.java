package com.lee.tms.modules.task.dto.body;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class DrawingAddBody
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
    @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime draftReceivedDate;
    /**
     * 校对接收时间
     */
    @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime proofreadReceivedDate;
    /**
     * QC 接收时间
     */
    @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime qcReceivedDate;
    /**
     * SQC 接收时间
     */
    @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime sqcReceivedDate;
    /**
     * 绘图下发时间
     */
    @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime draftIssuedDate;
    /**
     * 校对下发时间
     */
    @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime proofreadIssuedDate;
    /**
     * QC 下发时间
     */
    @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime qcIssuedDate;
    /**
     * SQC 下发时间
     */
    @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime sqcIssuedDate;
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
    @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime draftSubmittedDate;
    /**
     * 校对提交时间
     */
    @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime proofreadSubmittedDate;
    /**
     * QC 提交时间
     */
    @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime qcSubmittedDate;
    /**
     * SQC 提交时间
     */
    @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime sqcSubmittedDate;
    /**
     * 图纸任务截止日期
     */
    @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime taskDueDate;
    /**
     * QC 评分
     */
    private String qcRating;
    /**
     * QC 评分时间
     */
    @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime qcRatingDate;
}