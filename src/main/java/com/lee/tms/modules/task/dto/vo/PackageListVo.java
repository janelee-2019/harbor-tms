package com.lee.tms.modules.task.dto.vo;

import com.lee.tms.infrastructure.rest.vo.DefaultPageVo;
import com.lee.tms.modules.task.entity.Package;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

/**
 * 分包信息表分页
 */
@Setter
@Getter
public final class PackageListVo extends DefaultPageVo<Package>
{
    private Long totalSrId;
    private Long totalTeamId;
    private Long totalPackageQuantity;
    private BigDecimal totalPackageMh;
    private Long totalPackageWorkDays;
    private Long totalIssuerId;

    public PackageListVo(Integer pageNo, Integer pageSize, Long total, List<Package> records, Function<Package, PackageListItemVo> transform, Long totalSrId, Long totalTeamId,
                         Long totalPackageQuantity, BigDecimal totalPackageMh, Long totalPackageWorkDays, Long totalIssuerId)
    {
        super(pageNo, pageSize, total, records);
        this.totalSrId = totalSrId;
        this.totalTeamId = totalTeamId;
        this.totalPackageQuantity = totalPackageQuantity;
        this.totalPackageMh = totalPackageMh;
        this.totalPackageWorkDays = totalPackageWorkDays;
        this.totalIssuerId = totalIssuerId;
    }
}