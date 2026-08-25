package com.lee.tms.modules.system.dto.body;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CompanyAddBody
{
    protected String companyCode;

    @NotBlank (message = "参数 [companyName] 不能为空")
    protected String companyName;

    @NotBlank (message = "参数 [companyShortName] 不能为空")
    protected String companyShortName;

    protected String companyType;

    protected String companyRegNo;

    protected String companyCountry;

    protected String companyProvince;

    protected String companyCity;

    protected String companyAddress;

    protected String remark;
}
