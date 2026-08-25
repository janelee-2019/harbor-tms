package com.lee.tms.modules.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.tms.infrastructure.auth.AuthUser;
import com.lee.tms.infrastructure.rest.RestCode;
import com.lee.tms.infrastructure.rest.RestException;
import com.lee.tms.modules.system.dto.body.RoleAddBody;
import com.lee.tms.modules.system.dto.body.RoleUpdateBody;
import com.lee.tms.modules.system.dto.vo.RoleInfoVo;
import com.lee.tms.modules.system.entity.Function;
import com.lee.tms.modules.system.entity.Role;
import com.lee.tms.modules.system.entity.RoleFunction;
import com.lee.tms.modules.system.entity.UserRole;
import com.lee.tms.modules.system.mapper.RoleMapper;
import com.lee.tms.modules.system.service.FunctionService;
import com.lee.tms.modules.system.service.RoleFunctionService;
import com.lee.tms.modules.system.service.RoleService;
import com.lee.tms.modules.system.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService
{

    @Autowired
    private FunctionService functionService;

    @Autowired
    private RoleFunctionService roleFunctionService;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public Set<String> listRoleKeysByUserId(Long userId)
    {
        List<Role> roles = baseMapper.selectRolesByUserId(userId);
        return roles.stream().map(Role::getRoleKey).collect(Collectors.toSet());
    }

    @Override
    public RoleInfoVo getRoleInfo(Long roleId)
    {
        // 超级管理员不能编辑
        if (roleId == 0)
        {
            throw new RestException(RestCode.SYS_ERR, "超级管理员不能编辑");
        }
        // 检查角色是否存在
        Role role = getById(roleId);
        if (role == null)
        {
            throw new RestException(RestCode.SYS_ERR, "角色不存在 [ID = {}]", roleId);
        }

        RoleInfoVo vo = RoleInfoVo.fromModel(role);
        List<Function> functions = functionService.listFunctionsByRoleId(roleId, true);
        List<Long> functionIds = functions.stream().map(Function::getId).collect(Collectors.toList());
        vo.setFunctions(functionIds);
        return vo;
    }

    @Transactional (rollbackFor = Exception.class)
    @Override
    public void addRole(RoleAddBody body)
    {
        // 检查角色 Key 是否重复
        if (count(Wrappers.<Role>lambdaQuery().eq(Role::getRoleKey, body.getRoleKey())) > 0)
        {
            throw new RestException(RestCode.SYS_ERR, "角色 Key 重复 [Key = {}]", body.getRoleKey());
        }

        // 检查功能是否都存在
        List<Long> finalFunctionIds = getFunctionIdsBeforeAddOrUpdate(body.getFunctionIds());

        // 插入角色
        Role role = new Role();
        BeanUtil.copyProperties(body, role);

        AuthUser authUser = new AuthUser();
        LocalDateTime now = LocalDateTime.now();
        role.setCreateDate(now);
        role.setUpdateDate(now);
        role.setCreatorId(authUser.getId());
        role.setUpdaterId(authUser.getId());

        save(role);

        // 批量添加角色功能关联
        List<RoleFunction> roleFunctions = buildRoleFunctions(role.getId(), finalFunctionIds);
        roleFunctions.forEach(RoleFunction::insert);
    }

    @Transactional (rollbackFor = Exception.class)
    @Override
    public void updateRole(RoleUpdateBody body)
    {
        // 超级管理员不能编辑
        if (body.getId() == 0)
        {
            throw new RestException(RestCode.SYS_ERR, "超级管理员不能编辑");
        }

        // 检查角色是否存在
        Role role = getById(body.getId());
        if (role == null)
        {
            throw new RestException(RestCode.SYS_ERR, "角色不存在 [ID = {}]", body.getId());
        }

        // 检查功能是否都存在
        List<Long> finalFunctionIds = getFunctionIdsBeforeAddOrUpdate(body.getFunctionIds());

        // 更新角色
        BeanUtil.copyProperties(body, role);
        AuthUser authUser = new AuthUser();
        LocalDateTime now = LocalDateTime.now();
        role.setUpdateDate(now);
        role.setUpdaterId(authUser.getId());

        update(role,
               Wrappers.<Role>lambdaUpdate().set(role.getRoleLevel() == null, Role::getRoleLevel, 0L).set(StrUtil.isBlank(role.getRemark()), Role::getRemark, null).eq(Role::getId, role.getId()));

        // 先删除角色功能关联
        roleFunctionService.remove(Wrappers.<RoleFunction>lambdaUpdate().eq(RoleFunction::getRoleId, role.getId()));

        // 批量添加角色功能关联
        List<RoleFunction> roleFunctions = buildRoleFunctions(role.getId(), finalFunctionIds);
        roleFunctions.forEach(RoleFunction::insert);
    }

    @Transactional (rollbackFor = Exception.class)
    @Override
    public void deleteRole(Long roleId)
    {
        // 超级管理员不能编辑
        if (roleId == 0)
        {
            throw new RestException(RestCode.SYS_ERR, "超级管理员不能删除");
        }
        // 检查角色是否存在
        Role role = getById(roleId);
        if (role == null)
        {
            throw new RestException(RestCode.SYS_ERR, "角色不存在 [ID = {}]", roleId);
        }

        // 检查角色是否分配给用户
        if (userRoleService.count(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getRoleId, roleId)) > 0)
        {
            throw new RestException(RestCode.SYS_ERR, "角色已分配给用户，不允许删除 [ID = {}]", roleId);
        }

        // 级联删除角色与功能关联信息
        roleFunctionService.remove(Wrappers.<RoleFunction>lambdaUpdate().eq(RoleFunction::getRoleId, roleId));
        role.deleteById();
    }

    private List<Long> getFunctionIdsBeforeAddOrUpdate(List<Long> functionIds)
    {
        List<Function> functions = functionService.list(Wrappers.<Function>lambdaQuery().in(Function::getId, functionIds));
        if (functions.size() < functionIds.size())
        {
            throw new RestException(RestCode.SYS_ERR, "功能不存在");
        }
        // 防止出现未选择父节点的情况
        Set<Long> resultIds = new HashSet<>(functionIds.size());
        for (Function function : functions)
        {
            resultIds.add(function.getId());
            List<String> parentIds = StrUtil.split(function.getAncestors(), ',');
            for (String parentId : parentIds)
            {
                resultIds.add(Long.valueOf(parentId));
            }
        }
        // 去除顶级节点
        resultIds.remove(0L);
        return new ArrayList<>(resultIds);
    }

    private List<RoleFunction> buildRoleFunctions(Long roleId, List<Long> functionIds)
    {
        List<RoleFunction> roleFunctions = new ArrayList<>(functionIds.size());
        for (Long functionId : functionIds)
        {
            RoleFunction roleFunction = new RoleFunction();
            roleFunction.setRoleId(roleId);
            roleFunction.setFunctionId(functionId);
            roleFunctions.add(roleFunction);
        }
        return roleFunctions;
    }
}
