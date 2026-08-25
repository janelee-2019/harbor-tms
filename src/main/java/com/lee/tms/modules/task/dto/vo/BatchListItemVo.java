package com.lee.tms.modules.task.dto.vo;

import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lee.tms.modules.task.entity.Batch;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BatchListItemVo
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
    @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime batchTargetDate;
    /**
     * 批次完成时间
     */
    @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime batchCompletedDate;
    /**
     * 批次任务开始时间
     */
    @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime batchStartDate;
    /**
     * QC 评分
     */
    private String qcRating;
    /**
     * QC 完成时间
     */
    @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime qcCompletedDate;
    /**
     * 备注
     */
    private String remark;
    /**
     * 下发时间
     */
    @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime issueDate;
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

    public static BatchListItemVo fromModel(Batch e)
    {
        BatchListItemVo vo = new BatchListItemVo();
        BeanUtil.copyProperties(e, vo);
        return vo;
    }
}