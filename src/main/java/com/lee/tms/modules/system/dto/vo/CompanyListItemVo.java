package com.lee.tms.modules.system.dto.vo;

import cn.hutool.core.bean.BeanUtil;
import com.lee.tms.modules.system.entity.Company;
import lombok.Data;

@Data
public final class CompanyListItemVo
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

    public static CompanyListItemVo fromModel(Company company)
    {
        CompanyListItemVo vo = new CompanyListItemVo();
        BeanUtil.copyProperties(company, vo);
        return vo;
    }
}
