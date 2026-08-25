package com.lee.tms.modules.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.tms.infrastructure.rest.RestCode;
import com.lee.tms.infrastructure.rest.RestException;
import com.lee.tms.modules.system.dto.body.CompanyAddBody;
import com.lee.tms.modules.system.dto.body.CompanyUpdateBody;
import com.lee.tms.modules.system.entity.Company;
import com.lee.tms.modules.system.entity.Dept;
import com.lee.tms.modules.system.mapper.CompanyMapper;
import com.lee.tms.modules.system.service.CompanyService;
import com.lee.tms.modules.system.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService
{
    @Autowired
    private DeptService deptService;

    @Override
    public void addCompany(CompanyAddBody body)
    {
        Company company = new Company();
        BeanUtil.copyProperties(body, company);
        company.insert();
    }

    @Override
    public void updateCompany(CompanyUpdateBody body)
    {
        Company company = getById(body.getId());
        if (company == null)
        {
            throw new RestException(RestCode.SYS_ERR, "公司不存在 [ID = {}]", body.getId());
        }

        BeanUtil.copyProperties(body, company);
        company.updateById();
    }

    @Override
    public void deleteCompany(Long companyId)
    {
        Company company = getById(companyId);
        if (company == null)
        {
            throw new RestException(RestCode.SYS_ERR, "公司不存在 [ID = {}]", companyId);
        }
        if (deptService.count(Wrappers.<Dept>lambdaQuery().eq(Dept::getCompanyId, companyId)) > 0)
        {
            throw new RestException(RestCode.SYS_ERR, "该公司与部门关联，不能删除 [ID = {}]", companyId);
        }

        removeById(companyId);
    }

    @Override
    public Company getCompanyOrThrow(Wrapper<Company> queryWrapper, String message, Object... params)
    {
        Company company = getOne(queryWrapper);
        if (company == null)
        {
            throw new RestException(RestCode.SYS_ERR, message, params);
        }
        return company;
    }
}
