package com.lee.tms.modules.system.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.tms.modules.system.dto.body.CompanyAddBody;
import com.lee.tms.modules.system.dto.body.CompanyUpdateBody;
import com.lee.tms.modules.system.entity.Company;

/**
 * <p>
 * 公司信息表 服务类
 * </p>
 */
public interface CompanyService extends IService<Company>
{
    void addCompany(CompanyAddBody body);

    void updateCompany(CompanyUpdateBody body);

    void deleteCompany(Long companyId);

    Company getCompanyOrThrow(Wrapper<Company> queryWrapper, String message, Object... params);
}
