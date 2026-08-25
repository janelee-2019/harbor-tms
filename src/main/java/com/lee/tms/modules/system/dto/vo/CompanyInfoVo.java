package com.lee.tms.modules.system.dto.vo;

import cn.hutool.core.bean.BeanUtil;
import com.lee.tms.modules.system.entity.Company;
import lombok.Data;

@Data
public final class CompanyInfoVo
{
    private Long id;

    private String companyCode;

    private String companyName;

    private String companyShortName;

    private String companyType;

    private String companyRegNo;

    private String companyCountry;

    private String companyProvince;

    private String companyCity;

    private String companyAddress;

    private String remark;

    public static CompanyInfoVo fromModel(Company company)
    {
        CompanyInfoVo vo = new CompanyInfoVo();
        BeanUtil.copyProperties(company, vo);
        return vo;
    }
}
