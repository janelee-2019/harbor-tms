package com.lee.tms.modules.system.controller;

import com.lee.tms.infrastructure.log.ApiLog;
import com.lee.tms.infrastructure.rest.R;
import com.lee.tms.infrastructure.rest.RestCode;
import com.lee.tms.infrastructure.rest.RestException;
import com.lee.tms.infrastructure.rest.body.DefaultOpByIdBody;
import com.lee.tms.infrastructure.rest.query.DefaultInfoQuery;
import com.lee.tms.modules.system.dto.body.FunctionAddBody;
import com.lee.tms.modules.system.dto.body.FunctionUpdateBody;
import com.lee.tms.modules.system.dto.vo.FunctionInfoVo;
import com.lee.tms.modules.system.entity.Function;
import com.lee.tms.modules.system.service.FunctionService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 系统功能管理
 */
@RestController
@RequestMapping ("/sys/function")
@RequiredArgsConstructor
public class FunctionController
{
    private final FunctionService functionService;

    /**
     * 添加功能
     */
    @ApiLog (module = "系统管理", tag = "新增功能")
    @RequiresPermissions ("sys:function:add")
    @PostMapping ("add")
    public R addFunction(@Validated @RequestBody FunctionAddBody body)
    {
        functionService.addFunction(body);
        return R.ok();
    }

    /**
     * 修改功能
     */
    @ApiLog (module = "系统管理", tag = "更新功能")
    @RequiresPermissions ("sys:function:update")
    @PostMapping ("update")
    public R updateFunction(@Validated @RequestBody FunctionUpdateBody body)
    {
        functionService.updateFunction(body);
        return R.ok();
    }

    /**
     * 删除功能
     */
    @ApiLog (module = "系统管理", tag = "删除功能")
    @RequiresPermissions ("sys:function:delete")
    @PostMapping ("delete")
    public R deleteFunction(@Validated @RequestBody DefaultOpByIdBody body)
    {
        functionService.deleteFunction(body.getId());
        return R.ok();
    }

    /**
     * 获取功能信息
     */
    @RequiresPermissions ("sys:function:update")
    @GetMapping ("info")
    public R getFunctionInfo(@Validated DefaultInfoQuery query)
    {
        Function function = functionService.getById(query.getId());
        if (function == null)
        {
            throw new RestException(RestCode.SYS_ERR, "功能不存在 [ID = {}]", query.getId());
        }
        return R.ok(FunctionInfoVo.fromModel(function));
    }

    /**
     * 获取功能树
     */
    @RequiresPermissions ("sys:function:tree")
    @GetMapping ("tree")
    public R getFunctionTree()
    {
        return R.ok(functionService.getAllFunctionsTree());
    }
}
