package com.lee.tms.modules.system.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.tms.infrastructure.log.ApiLog;
import com.lee.tms.infrastructure.rest.R;
import com.lee.tms.infrastructure.rest.body.DefaultOpByIdBody;
import com.lee.tms.infrastructure.rest.query.DefaultInfoQuery;
import com.lee.tms.infrastructure.rest.vo.DefaultPageVo;
import com.lee.tms.modules.system.dto.body.RoleAddBody;
import com.lee.tms.modules.system.dto.body.RoleUpdateBody;
import com.lee.tms.modules.system.dto.query.RolePageQuery;
import com.lee.tms.modules.system.dto.vo.RoleSimpleVo;
import com.lee.tms.modules.system.entity.Role;
import com.lee.tms.modules.system.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色管理
 */
@RestController
@RequestMapping ("/sys/role")
@RequiredArgsConstructor
public class RoleController
{
    private final RoleService roleService;

    /**
     * 添加角色
     */
    @ApiLog (module = "系统管理", tag = "新增角色")
    @RequiresPermissions ("sys:role:add")
    @PostMapping ("add")
    public R addRole(@Validated @RequestBody RoleAddBody body)
    {
        roleService.addRole(body);
        return R.ok();
    }

    /**
     * 修改角色
     */
    @ApiLog (module = "系统管理", tag = "更新角色")
    @RequiresPermissions ("sys:role:update")
    @PostMapping ("update")
    public R updateRole(@Validated @RequestBody RoleUpdateBody body)
    {
        roleService.updateRole(body);
        return R.ok();
    }

    /**
     * 删除角色
     */
    @ApiLog (module = "系统管理", tag = "删除角色")
    @RequiresPermissions ("sys:role:delete")
    @PostMapping ("delete")
    public R deleteRole(@Validated @RequestBody DefaultOpByIdBody body)
    {
        roleService.deleteRole(body.getId());
        return R.ok();
    }

    /**
     * 获取角色信息
     */
    @RequiresPermissions ("sys:role:update")
    @GetMapping ("info")
    public R getRoleInfo(@Validated DefaultInfoQuery query)
    {
        return R.ok(roleService.getRoleInfo(query.getId()));
    }

    /**
     * 获取角色列表
     */
    @GetMapping ("list")
    public R getRoleList()
    {
        List<Role> roles = roleService.list(Wrappers.<Role>lambdaQuery().ne(Role::getId, 0));
        List<RoleSimpleVo> vos = roles.stream().map(RoleSimpleVo::fromModel).collect(Collectors.toList());
        return R.ok(vos);
    }

    /**
     * 获取角色分页列表
     */
    @RequiresPermissions ("sys:role:page")
    @GetMapping ("page")
    public R getRolePage(@Validated RolePageQuery query)
    {
        IPage<Role> page = new Page<>(query.getPageNo(), query.getPageSize());
        Wrapper<Role> wrapper = Wrappers.<Role>lambdaQuery().ne(Role::getId, 0L).like(StrUtil.isNotBlank(query.getRoleName()), Role::getRoleName, query.getRoleName()).orderByAsc(Role::getRoleLevel);
        roleService.page(page, wrapper);
        DefaultPageVo<Role> vo = new DefaultPageVo<>(query.getPageNo(), query.getPageSize(), page.getTotal(), page.getRecords());
        return R.ok(vo);
    }
}
