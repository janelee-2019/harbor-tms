package com.lee.tms.modules.system.dto.vo;

import cn.hutool.core.bean.BeanUtil;
import com.lee.tms.modules.system.entity.Company;
import lombok.Data;

@Data
public final class CompanySimpleVo
{
    private Long id;

    private String companyName;

    private String companyShortName;

    public static CompanySimpleVo fromModel(Company company)
    {
        CompanySimpleVo vo = new CompanySimpleVo();
        BeanUtil.copyProperties(company, vo);
        return vo;
    }
}
