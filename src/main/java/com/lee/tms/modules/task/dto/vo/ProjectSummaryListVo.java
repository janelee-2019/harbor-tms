package com.lee.tms.modules.task.dto.vo;

import com.lee.tms.infrastructure.rest.vo.DefaultPageVo;
import com.lee.tms.modules.task.entity.ProjectSummary;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

@Setter
@Getter
public final class ProjectSummaryListVo extends DefaultPageVo<ProjectSummary>
{
    private Long totalAffiliateId;
    private Long totalSrPlanWeeks;
    private BigDecimal totalApprovedMh;
    private BigDecimal totalConsumedMh;
    private BigDecimal totalRemainingMh;
    private BigDecimal totalTaskProgress;
    private BigDecimal totalBudgetAwarded;
    private Long totalSrActualExecutionDays;
    private Long totalAfterLastSubmitToSabicForApprovalDay;
    private Long totalExecutionSrDurationDay;

    public ProjectSummaryListVo(Integer pageNo, Integer pageSize, Long total, List<ProjectSummary> records, Function<ProjectSummary, ProjectSummaryListItemVo> transform, Long totalAffiliateId,
                                Long totalSrPlanWeeks, BigDecimal totalApprovedMh, BigDecimal totalConsumedMh, BigDecimal totalRemainingMh, BigDecimal totalTaskProgress, BigDecimal totalBudgetAwarded,
                                Long totalSrActualExecutionDays, Long totalAfterLastSubmitToSabicForApprovalDay, Long totalExecutionSrDurationDay)
    {
        super(pageNo, pageSize, total, records);
        this.totalAffiliateId = totalAffiliateId;
        this.totalSrPlanWeeks = totalSrPlanWeeks;
        this.totalApprovedMh = totalApprovedMh;
        this.totalConsumedMh = totalConsumedMh;
        this.totalRemainingMh = totalRemainingMh;
        this.totalTaskProgress = totalTaskProgress;
        this.totalBudgetAwarded = totalBudgetAwarded;
        this.totalSrActualExecutionDays = totalSrActualExecutionDays;
        this.totalAfterLastSubmitToSabicForApprovalDay = totalAfterLastSubmitToSabicForApprovalDay;
        this.totalExecutionSrDurationDay = totalExecutionSrDurationDay;
    }
}