package com.lee.tms.modules.task.dto.vo;

import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lee.tms.modules.task.entity.Package;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PackageListItemVo
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
    @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime packageStartDate;
    /**
     * 包任务完成时间
     */
    @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime packageCompletedDate;
    /**
     * 工作日天数
     */
    private Long packageWorkDays;
    /**
     * 包下发时间
     */
    @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime issueDate;
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
    @JsonFormat (pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime packageTargetDate;
    /**
     * 是否已下发
     */
    private String isIssued;
    /**
     * 是否已分批次
     */
    private String isDistributed;

    public static PackageListItemVo fromModel(Package e)
    {
        PackageListItemVo vo = new PackageListItemVo();
        BeanUtil.copyProperties(e, vo);
        return vo;
    }
}