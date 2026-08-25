package com.lee.tms.modules.system.controller;

import com.lee.tms.infrastructure.log.ApiLog;
import com.lee.tms.infrastructure.rest.R;
import com.lee.tms.infrastructure.rest.RestCode;
import com.lee.tms.infrastructure.rest.RestException;
import com.lee.tms.infrastructure.rest.body.DefaultOpByIdBody;
import com.lee.tms.infrastructure.rest.query.DefaultInfoQuery;
import com.lee.tms.modules.system.dto.body.DeptAddBody;
import com.lee.tms.modules.system.dto.body.DeptUpdateBody;
import com.lee.tms.modules.system.dto.query.DeptPageQuery;
import com.lee.tms.modules.system.dto.query.DeptTreeQuery;
import com.lee.tms.modules.system.dto.vo.DeptInfoVo;
import com.lee.tms.modules.system.entity.Dept;
import com.lee.tms.modules.system.service.DeptService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 部门管理
 */
@RestController
@RequestMapping ("/sys/dept")
@RequiredArgsConstructor
public class DeptController
{
    private final DeptService deptService;

    /**
     * 添加部门
     */
    @ApiLog (module = "系统管理", tag = "新增部门")
    @RequiresPermissions ("sys:dept:add")
    @PostMapping ("add")
    public R addDept(@Validated @RequestBody DeptAddBody body)
    {
        deptService.addDept(body);
        return R.ok();
    }

    /**
     * 修改部门
     */
    @ApiLog (module = "系统管理", tag = "更新部门")
    @RequiresPermissions ("sys:dept:update")
    @PostMapping ("update")
    public R updateDept(@Validated @RequestBody DeptUpdateBody body)
    {
        deptService.updateDept(body);
        return R.ok();
    }

    /**
     * 刪除部门
     */
    @ApiLog (module = "系统管理", tag = "删除部门")
    @RequiresPermissions ("sys:dept:delete")
    @PostMapping ("delete")
    public R deleteDept(@Validated @RequestBody DefaultOpByIdBody body)
    {
        deptService.deleteDept(body.getId());
        return R.ok();
    }

    /**
     * 获取部门信息
     */
    @RequiresPermissions ("sys:dept:update")
    @GetMapping ("info")
    public R getDeptInfo(@Validated DefaultInfoQuery query)
    {
        Dept dept = deptService.getById(query.getId());
        if (dept == null)
        {
            throw new RestException(RestCode.SYS_ERR, "部门不存在 [ID = {}]", query.getId());
        }
        return R.ok(DeptInfoVo.fromModel(dept));
    }

    /**
     * 获取部门分页列表
     */
    @RequiresPermissions ("sys:dept:page")
    @GetMapping ("page")
    public R getDeptPage(@Validated DeptPageQuery query)
    {
        return R.ok(deptService.paginateDept(query));
    }

    /**
     * 获取部门树形列表
     */
    @GetMapping ("tree")
    public R getDeptTree(DeptTreeQuery query)
    {
        return R.ok(deptService.getDeptTree(query));
    }
}
