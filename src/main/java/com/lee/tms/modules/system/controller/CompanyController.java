package com.lee.tms.modules.system.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.tms.infrastructure.log.ApiLog;
import com.lee.tms.infrastructure.rest.R;
import com.lee.tms.infrastructure.rest.RestCode;
import com.lee.tms.infrastructure.rest.RestException;
import com.lee.tms.infrastructure.rest.body.DefaultOpByIdBody;
import com.lee.tms.infrastructure.rest.query.DefaultInfoQuery;
import com.lee.tms.infrastructure.rest.vo.DefaultPageVo;
import com.lee.tms.modules.system.dto.body.CompanyAddBody;
import com.lee.tms.modules.system.dto.body.CompanyUpdateBody;
import com.lee.tms.modules.system.dto.query.CompanyPageQuery;
import com.lee.tms.modules.system.dto.vo.CompanyInfoVo;
import com.lee.tms.modules.system.dto.vo.CompanySimpleVo;
import com.lee.tms.modules.system.entity.Company;
import com.lee.tms.modules.system.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 公司
 */
@RestController
@RequestMapping ("/sys/company")
@RequiredArgsConstructor
public class CompanyController
{
    private final CompanyService companyService;

    /**
     * 添加公司
     */
    @ApiLog (module = "系统管理", tag = "新增公司")
    @RequiresPermissions ("sys:company:add")
    @PostMapping ("add")
    public R addCompany(@Validated @RequestBody CompanyAddBody body)
    {
        companyService.addCompany(body);
        return R.ok();
    }

    /**
     * 修改公司
     */
    @ApiLog (module = "系统管理", tag = "更新公司")
    @RequiresPermissions ("sys:company:update")
    @PostMapping ("update")
    public R updateCompany(@Validated @RequestBody CompanyUpdateBody body)
    {
        companyService.updateCompany(body);
        return R.ok();
    }

    /**
     * 删除公司
     */
    @ApiLog (module = "系统管理", tag = "删除公司")
    @RequiresPermissions ("sys:company:delete")
    @PostMapping ("delete")
    public R deleteCompany(@Validated @RequestBody DefaultOpByIdBody body)
    {
        companyService.deleteCompany(body.getId());
        return R.ok();
    }

    /**
     * 获取公司信息
     */
    @RequiresPermissions (value = "sys:company:update")
    @GetMapping ("info")
    public R getCompanyInfo(@Validated DefaultInfoQuery query)
    {
        Company company = companyService.getById(query.getId());
        if (company == null)
        {
            throw new RestException(RestCode.SYS_ERR, "公司不存在 [ID = {}]", query.getId());
        }
        return R.ok(CompanyInfoVo.fromModel(company));
    }

    /**
     * 获取公司列表
     */
    @GetMapping ("list")
    public R getCompanyList()
    {
        List<Company> companies = companyService.list();
        List<CompanySimpleVo> vos = companies.stream().map(CompanySimpleVo::fromModel).collect(Collectors.toList());
        return R.ok(vos);
    }

    /**
     * 获取公司分页列表
     */
    @RequiresPermissions ("sys:company:page")
    @GetMapping ("page")
    public R getCompanyPage(@Validated CompanyPageQuery query)
    {
        IPage<Company> page = new Page<>(query.getPageNo(), query.getPageSize());
        Wrapper<Company> wrapper = Wrappers.<Company>lambdaQuery().like(StrUtil.isNotBlank(query.getCompanyName()), Company::getCompanyName, query.getCompanyName());
        companyService.page(page, wrapper);
        DefaultPageVo<Company> vo = new DefaultPageVo<>(query.getPageNo(), query.getPageSize(), page.getTotal(), page.getRecords());
        return R.ok(vo);
    }
}
