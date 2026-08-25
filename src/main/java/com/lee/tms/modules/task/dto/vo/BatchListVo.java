package com.lee.tms.modules.task.dto.vo;

import com.lee.tms.infrastructure.rest.vo.DefaultPageVo;
import com.lee.tms.modules.task.entity.Batch;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

@Setter
@Getter
public final class BatchListVo extends DefaultPageVo<Batch>
{
    private Long totalSrId;
    private Long totalPackageId;
    private Long totalGroupId;
    private Long totalBatchQuantity;
    private BigDecimal totalBatchSpecMh;
    private BigDecimal totalBatchIssueMh;
    private Long totalIssuerId;
    private Long totalBatchWorkDays;
    private Long totalQcId;

    public BatchListVo(Integer pageNo, Integer pageSize, Long total, List<Batch> records, Function<Batch, BatchListItemVo> transform, Long totalSrId, Long totalPackageId, Long totalGroupId,
                       Long totalBatchQuantity, BigDecimal totalBatchSpecMh, BigDecimal totalBatchIssueMh, Long totalIssuerId, Long totalBatchWorkDays, Long totalQcId)
    {
        super(pageNo, pageSize, total, records);
        this.totalSrId = totalSrId;
        this.totalPackageId = totalPackageId;
        this.totalGroupId = totalGroupId;
        this.totalBatchQuantity = totalBatchQuantity;
        this.totalBatchSpecMh = totalBatchSpecMh;
        this.totalBatchIssueMh = totalBatchIssueMh;
        this.totalIssuerId = totalIssuerId;
        this.totalBatchWorkDays = totalBatchWorkDays;
        this.totalQcId = totalQcId;
    }
}