package com.lee.tms.modules.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.tms.infrastructure.rest.RestCode;
import com.lee.tms.infrastructure.rest.RestException;
import com.lee.tms.infrastructure.rest.vo.DefaultPageVo;
import com.lee.tms.infrastructure.util.TreeBuilder;
import com.lee.tms.modules.system.dto.body.DeptAddBody;
import com.lee.tms.modules.system.dto.body.DeptUpdateBody;
import com.lee.tms.modules.system.dto.query.DeptPageQuery;
import com.lee.tms.modules.system.dto.query.DeptTreeQuery;
import com.lee.tms.modules.system.dto.vo.DeptTreeVo;
import com.lee.tms.modules.system.entity.Company;
import com.lee.tms.modules.system.entity.Dept;
import com.lee.tms.modules.system.entity.User;
import com.lee.tms.modules.system.mapper.DeptMapper;
import com.lee.tms.modules.system.service.CompanyService;
import com.lee.tms.modules.system.service.DeptService;
import com.lee.tms.modules.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService
{
    @Lazy
    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    @Override
    public void addDept(DeptAddBody body)
    {
        Dept dept = new Dept();

        BeanUtil.copyProperties(body, dept);

        // 检查公司是否存在
        if (companyService.count(Wrappers.<Company>lambdaQuery().eq(Company::getId, body.getCompanyId())) < 1)
        {
            throw new RestException(RestCode.SYS_ERR, "公司不存在 [ID = {}]", body.getCompanyId());
        }

        // 如果不是挂在顶级下，需要判断父部门是否存在
        Dept parent = null;
        if (body.getParentId() != 0)
        {
            parent = getById(body.getParentId());
            if (parent == null)
            {
                throw new RestException(RestCode.SYS_ERR, "父级部门不存在 [ID = {}]", body.getParentId());
            }
        }

        // 判断是否挂在顶级部门下
        if (parent != null)
        {
            dept.setAncestors(parent.getAncestors() + "," + parent.getId());
        }
        else
        {
            dept.setAncestors("0");
        }

        dept.insert();
    }

    @Transactional (rollbackFor = Exception.class)
    @Override
    public void updateDept(DeptUpdateBody body)
    {
        if (Objects.equals(body.getId(), body.getParentId()))
        {
            throw new RestException(RestCode.SYS_ERR, "父级部门不能设置为当前部门 [ID = {}]", body.getId());
        }
        // 检查部门是否存在
        Dept dept = getById(body.getId());
        if (dept == null)
        {
            throw new RestException(RestCode.SYS_ERR, "部门不存在 [ID = {}]", body.getId());
        }
        // 检查公司是否存在
        if (companyService.count(Wrappers.<Company>lambdaQuery().eq(Company::getId, body.getCompanyId())) < 1)
        {
            throw new RestException(RestCode.SYS_ERR, "公司不存在 [ID = {}]", body.getCompanyId());
        }

        BeanUtil.copyProperties(body, dept);

        // 如果不是挂在顶级下，需要判断父部门是否存在
        Dept parent = null;
        if (body.getParentId() != 0)
        {
            parent = getById(body.getParentId());
            if (parent == null)
            {
                throw new RestException(RestCode.SYS_ERR, "父级部门不存在 [ID = {}]", body.getParentId());
            }
        }

        // 临时保存旧祖级列表
        String oldAncestors = dept.getAncestors();

        // 判断是否挂在顶级部门下
        if (parent != null)
        {
            // 更新时 pid 不能设置为子节点
            if (dept.getId() != null)
            {
                if (StrUtil.split(parent.getAncestors(), ',').contains(dept.getId().toString()))
                {
                    throw new RestException(RestCode.SYS_ERR, "父级部门不能设置为当前部门的子节点");
                }
            }
            dept.setAncestors(parent.getAncestors() + "," + parent.getId());
        }
        else
        {
            dept.setAncestors("0");
        }
        update(dept, Wrappers.<Dept>lambdaUpdate().set(StrUtil.isBlank(dept.getDeptCode()), Dept::getDeptCode, null).set(dept.getDeptLevel() == null, Dept::getDeptLevel, 0L)
                             .set(StrUtil.isBlank(dept.getRemark()), Dept::getRemark, null).eq(Dept::getId, dept.getId()));

        // 更新所有子部门的祖级列表
        List<Dept> childDepts = baseMapper.selectChildDepts(dept.getId());
        for (Dept d : childDepts)
        {
            d.setAncestors(StrUtil.replace(d.getAncestors(), oldAncestors, dept.getAncestors()));
        }
        childDepts.forEach(Dept::updateById);
    }

    @Override
    public void deleteDept(Long deptId)
    {
        if (count(Wrappers.<Dept>lambdaQuery().eq(Dept::getParentId, deptId)) > 0)
        {
            throw new RestException(RestCode.SYS_ERR, "当前部门存在子部门，不允许删除 [ID = {}]", deptId);
        }
        if (userService.count(Wrappers.<User>lambdaQuery().eq(User::getDeptId, deptId)) > 0)
        {
            throw new RestException(RestCode.SYS_ERR, "部门下存在用户，不允许删除 [ID = {}]", deptId);
        }
        removeById(deptId);
    }

    @Override
    public DefaultPageVo<Dept> paginateDept(DeptPageQuery query)
    {
        IPage<Dept> page = new Page<>(query.getPageNo(), query.getPageSize());
        Wrapper<Dept> wrapper = Wrappers.<Dept>lambdaQuery().like(StrUtil.isNotBlank(query.getDeptName()), Dept::getDeptName, query.getDeptName());
        baseMapper.selectDeptPage(page, wrapper);
        return new DefaultPageVo<>(query.getPageNo(), query.getPageSize(), page.getTotal(), page.getRecords());
    }

    @Override
    public List<DeptTreeVo> getAllDeptTree()
    {
        List<DeptTreeVo> depts = list().stream().map(DeptTreeVo::fromModel).collect(Collectors.toList());
        return TreeBuilder.buildTree(depts);
    }

    @Override
    public List<DeptTreeVo> getDeptTree(DeptTreeQuery query)
    {
        Wrapper<Dept> wrapper = Wrappers.<Dept>lambdaQuery().eq(StrUtil.isNotBlank(query.getDeptCode()), Dept::getDeptCode, query.getDeptCode())
                                        .like(StrUtil.isNotBlank(query.getDeptName()), Dept::getDeptName, query.getDeptName());
        List<Dept> parents = list(wrapper);
        List<Dept> resultList = new LinkedList<>();
        if (query.getIncludeParent())
        {
            resultList.addAll(parents);
        }
        for (Dept dept : parents)
        {
            List<Dept> children = baseMapper.selectChildDepts(dept.getId());
            resultList.addAll(children);
        }
        return TreeBuilder.buildTree(resultList.stream().map(DeptTreeVo::fromModel).collect(Collectors.toList()));
    }

    @Override
    public Dept getDeptOrThrow(Wrapper<Dept> queryWrapper, String message, Object... params)
    {
        Dept dept = getOne(queryWrapper);
        if (dept == null)
        {
            throw new RestException(RestCode.SYS_ERR, message, params);
        }
        return dept;
    }

    @Override
    public List<Dept> listChildDept(Long parentDeptId)
    {
        return baseMapper.selectChildDepts(parentDeptId);
    }
}
