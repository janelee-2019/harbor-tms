package com.lee.tms.modules.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.tms.infrastructure.rest.RestCode;
import com.lee.tms.infrastructure.rest.RestException;
import com.lee.tms.infrastructure.util.TreeBuilder;
import com.lee.tms.modules.system.dto.body.FunctionAddBody;
import com.lee.tms.modules.system.dto.body.FunctionUpdateBody;
import com.lee.tms.modules.system.dto.vo.FunctionMenuVo;
import com.lee.tms.modules.system.dto.vo.FunctionTreeVo;
import com.lee.tms.modules.system.entity.Function;
import com.lee.tms.modules.system.entity.RoleFunction;
import com.lee.tms.modules.system.mapper.FunctionMapper;
import com.lee.tms.modules.system.service.FunctionService;
import com.lee.tms.modules.system.service.RoleFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FunctionServiceImpl extends ServiceImpl<FunctionMapper, Function> implements FunctionService
{
    @Autowired
    private RoleFunctionService roleFunctionService;

    @Override
    public Set<String> listFunctionKeysByUserId(Long userId)
    {
        return new HashSet<>(baseMapper.selectFunctionKeysByUserId(userId));
    }

    @Override
    public List<Function> listFunctionsByRoleId(Long roleId, boolean strictly)
    {
        return strictly ? baseMapper.selectFunctionsStrictlyByRoleId(roleId) : baseMapper.selectFunctionsByRoleId(roleId);
    }

    @Override
    public List<FunctionTreeVo> getAllFunctionsTree()
    {
        List<FunctionTreeVo> functions = baseMapper.selectAllFunctions().stream().map(FunctionTreeVo::fromModel).collect(Collectors.toList());
        return TreeBuilder.buildTree(functions);
    }

    @Override
    public List<FunctionTreeVo> getFunctionTreeByRoleId(Long roleId)
    {
        List<FunctionTreeVo> functions = baseMapper.selectFunctionsByRoleId(roleId).stream().map(FunctionTreeVo::fromModel).collect(Collectors.toList());
        return TreeBuilder.buildTree(functions);
    }

    @Override
    public List<FunctionMenuVo> getAllFunctionMenusTree()
    {
        List<FunctionMenuVo> menus = baseMapper.selectAllFunctionMenus().stream().map(FunctionMenuVo::fromModel).collect(Collectors.toList());
        return TreeBuilder.buildTree(menus);
    }

    @Override
    public List<FunctionMenuVo> getFunctionMenusTreeByUserId(Long userId)
    {
        List<FunctionMenuVo> menus = baseMapper.selectFunctionMenusByUserId(userId).stream().map(FunctionMenuVo::fromModel).collect(Collectors.toList());
        return TreeBuilder.buildTree(menus);
    }

    @Override
    public void addFunction(FunctionAddBody body)
    {
        Function function = new Function();

        BeanUtil.copyProperties(body, function);
        function.setVueTitle(body.getFunctionName());

        checkFunctionKeyUniqueOrThrow(function);

        Function parent = null;
        // 如果不是挂在顶级下，需要判断父功能是否存在
        if (function.getParentId() != 0)
        {
            parent = getById(function.getParentId());
            if (parent == null)
            {
                throw new RestException(RestCode.SYS_ERR, "父级功能不存在 [ID = {}]", function.getParentId());
            }
        }

        // 判断是否挂在顶级功能下
        if (parent != null)
        {
            function.setAncestors(parent.getAncestors() + "," + parent.getId());
        }
        else
        {
            function.setAncestors("0");
        }

        function.insert();
    }

    @Transactional (rollbackFor = Exception.class)
    @Override
    public void updateFunction(FunctionUpdateBody body)
    {
        if (Objects.equals(body.getId(), body.getParentId()))
        {
            throw new RestException(RestCode.SYS_ERR, "父级功能不能设置为当前功能 [ID = {}]", body.getId());
        }
        Function function = getById(body.getId());
        if (function == null)
        {
            throw new RestException(RestCode.SYS_ERR, "功能不存在 [ID = {}]", body.getId());
        }

        BeanUtil.copyProperties(body, function);
        function.setVueTitle(body.getFunctionName());

        checkFunctionKeyUniqueOrThrow(function);

        Function parent = null;
        // 如果不是挂在顶级下，需要判断父功能是否存在
        if (function.getParentId() != 0)
        {
            parent = getById(function.getParentId());
            if (parent == null)
            {
                throw new RestException(RestCode.SYS_ERR, "父级功能不存在 [ID = {}]", function.getParentId());
            }
        }

        // 临时保存旧祖级列表
        String oldAncestors = function.getAncestors();

        // 判断是否挂在顶级功能下
        if (parent != null)
        {
            // 更新时 pid 不能设置为子节点
            if (function.getId() != null)
            {
                if (StrUtil.split(parent.getAncestors(), ',').contains(function.getId().toString()))
                {
                    throw new RestException(RestCode.SYS_ERR, "父级功能不能设置为当前功能的子节点");
                }
            }
            function.setAncestors(parent.getAncestors() + "," + parent.getId());
        }
        else
        {
            function.setAncestors("0");
        }
        function.updateById();

        // 更新所有子功能的祖级列表
        List<Function> childFunctions = baseMapper.selectChildFunctions(function.getId());
        for (Function f : childFunctions)
        {
            f.setAncestors(StrUtil.replace(f.getAncestors(), oldAncestors, function.getAncestors()));
        }
        childFunctions.forEach(Function::updateById);
    }

    @Override
    public void deleteFunction(Long functionId)
    {
        if (count(Wrappers.<Function>lambdaQuery().eq(Function::getParentId, functionId)) > 0)
        {
            throw new RestException(RestCode.SYS_ERR, "当前功能存在子功能，不允许删除 [ID = {}]", functionId);
        }
        if (roleFunctionService.count(Wrappers.<RoleFunction>lambdaQuery().eq(RoleFunction::getFunctionId, functionId)) > 0)
        {
            throw new RestException(RestCode.SYS_ERR, "当前功能已分配给角色，不允许删除 [ID = {}]", functionId);
        }
        removeById(functionId);
    }

    private void checkFunctionKeyUniqueOrThrow(Function function)
    {
        Wrapper<Function> wrapper = Wrappers.<Function>lambdaQuery().eq(Function::getFunctionKey, function.getFunctionKey()).ne(function.getId() != null, Function::getId, function.getId());
        if (count(wrapper) > 0)
        {
            throw new RestException(RestCode.SYS_ERR, "功能 Key 重复 [Key = {}]", function.getFunctionKey());
        }
    }
}
