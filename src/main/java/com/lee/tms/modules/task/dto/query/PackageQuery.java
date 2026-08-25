package com.lee.tms.modules.task.dto.query;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PackageQuery
{
    /**
     * 服务请求 ID
     */
    private Long srId;
    /**
     * 包序号
     */
    private String packageNo;
    /**
     * 包类型
     */
    private String packageType;
    /**
     * 负责团队 ID
     */
    private Long teamId;
    /**
     * 包中的图纸或文档数量
     */
    private Long packageQuantity;
    /**
     * 包工时
     */
    private BigDecimal packageMh;
    /**
     * 包任务开始时间
     */
    private String packageStartDate;
    /**
     * 包任务完成时间
     */
    private String packageCompletedDate;
    /**
     * 工作日天数
     */
    private Long packageWorkDays;
    /**
     * 包下发时间
     */
    private String issueDate;
    /**
     * 下发者
     */
    private Long issuerId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 内部序号
     */
    private String sn;
    /**
     * 包任务目标完成时间
     */
    private String packageTargetDate;
    /**
     * 是否已下发
     */
    private String isIssued;
    /**
     * 是否已分批次
     */
    private String isDistributed;
    /**
     * 查询关键字，支持产品名称、产品简称和产品代码模糊查询
     */
    private String keyword;

    public LocalDateTime getPackageStartDate()
    {
        if (StrUtil.isNotBlank(packageStartDate))
        {
            return LocalDateTimeUtil.parse(packageStartDate, "yyyy-MM-dd");
        }
        return null;
    }

    public LocalDateTime getPackageCompletedDate()
    {
        if (StrUtil.isNotBlank(packageCompletedDate))
        {
            return LocalDateTimeUtil.parse(packageCompletedDate, "yyyy-MM-dd");
        }
        return null;
    }

    public LocalDateTime getIssueDate()
    {
        if (StrUtil.isNotBlank(issueDate))
        {
            return LocalDateTimeUtil.parse(issueDate, "yyyy-MM-dd");
        }
        return null;
    }

    public LocalDateTime getPackageTargetDate()
    {
        if (StrUtil.isNotBlank(packageTargetDate))
        {
            return LocalDateTimeUtil.parse(packageTargetDate, "yyyy-MM-dd");
        }
        return null;
    }
}