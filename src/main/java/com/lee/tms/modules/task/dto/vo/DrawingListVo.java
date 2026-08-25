package com.lee.tms.modules.task.dto.vo;

import com.lee.tms.infrastructure.rest.vo.DefaultPageVo;
import com.lee.tms.modules.task.entity.Drawing;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

@Setter
@Getter
public final class DrawingListVo extends DefaultPageVo<Drawing>
{
    private Long totalSrId;
    private Long totalPackageId;
    private Long totalBatchId;
    private Long totalDrawingId;
    private BigDecimal totalDraftMh;
    private Long totalDrafterId;
    private BigDecimal totalProofreadMh;
    private Long totalProofreaderId;
    private Long totalQcId;
    private Long totalSqcId;
    private Long totalDraftIssuerId;
    private Long totalProofreadIssuerId;
    private Long totalQcIssuerId;
    private Long totalSqcIssuerId;

    public DrawingListVo(Integer pageNo, Integer pageSize, Long total, List<Drawing> records, Function<Drawing, DrawingListItemVo> transform, Long totalSrId, Long totalPackageId, Long totalBatchId,
                         Long totalDrawingId, BigDecimal totalDraftMh, Long totalDrafterId, BigDecimal totalProofreadMh, Long totalProofreaderId, Long totalQcId, Long totalSqcId,
                         Long totalDraftIssuerId, Long totalProofreadIssuerId, Long totalQcIssuerId, Long totalSqcIssuerId)
    {
        super(pageNo, pageSize, total, records);
        this.totalSrId = totalSrId;
        this.totalPackageId = totalPackageId;
        this.totalBatchId = totalBatchId;
        this.totalDrawingId = totalDrawingId;
        this.totalDraftMh = totalDraftMh;
        this.totalDrafterId = totalDrafterId;
        this.totalProofreadMh = totalProofreadMh;
        this.totalProofreaderId = totalProofreaderId;
        this.totalQcId = totalQcId;
        this.totalSqcId = totalSqcId;
        this.totalDraftIssuerId = totalDraftIssuerId;
        this.totalProofreadIssuerId = totalProofreadIssuerId;
        this.totalQcIssuerId = totalQcIssuerId;
        this.totalSqcIssuerId = totalSqcIssuerId;
    }
}