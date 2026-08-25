package com.lee.tms.modules.task.dto.query;

import com.lee.tms.infrastructure.rest.query.DefaultPageQuery;
import lombok.Data;

@Data
public class DrawingPageQuery extends DefaultPageQuery
{
    /**
     * 服务请求 ID
     */
    private String srId;
    /**
     * 包 ID
     */
    private String packageId;
    /**
     * 批次 ID
     */
    private String batchId;
    /**
     * 图纸内部 ID
     */
    private String drawingId;
    /**
     * 绘图员 ID
     */
    private String drafterId;
    /**
     * 校对员 ID
     */
    private String proofreaderId;
    /**
     * 初级质控员 ID
     */
    private String qcId;
    /**
     * 高级质控员 ID
     */
    private String sqcId;
    /**
     * 流程 ID
     */
    private String procInstId;
    /**
     * 绘图下发者
     */
    private String draftIssuerId;
    /**
     * 校对下发者
     */
    private String proofreadIssuerId;
    /**
     * QC 下发者
     */
    private String qcIssuerId;
    /**
     * SQC 下发者
     */
    private String sqcIssuerId;

    /**
     * 查询关键字，支持文本字段的模糊查询
     */
    private String keyword;
}