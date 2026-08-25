package com.lee.tms.modules.system.controller;

import com.lee.tms.infrastructure.log.ApiLog;
import com.lee.tms.infrastructure.rest.R;
import com.lee.tms.infrastructure.rest.body.DefaultOpByIdBody;
import com.lee.tms.infrastructure.rest.query.DefaultInfoQuery;
import com.lee.tms.modules.system.dto.body.UserAddBody;
import com.lee.tms.modules.system.dto.body.UserPasswordResetBody;
import com.lee.tms.modules.system.dto.body.UserPasswordUpdateBody;
import com.lee.tms.modules.system.dto.body.UserUpdateBody;
import com.lee.tms.modules.system.dto.query.UserListQuery;
import com.lee.tms.modules.system.dto.query.UserPageQuery;
import com.lee.tms.modules.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 */
@RestController
@RequestMapping ("/sys/user")
@RequiredArgsConstructor
public class UserController
{
    private final UserService userService;

    /**
     * 添加用户
     */
    @ApiLog (module = "系统管理", tag = "新增用户")
    @RequiresPermissions ("sys:user:add")
    @PostMapping ("add")
    public R addUser(@Validated @RequestBody UserAddBody body)
    {
        userService.addUser(body);
        return R.ok();
    }

    /**
     * 更新用户
     */
    @ApiLog (module = "系统管理", tag = "更新用户")
    @RequiresPermissions ("sys:user:update")
    @PostMapping ("update")
    public R updateUser(@Validated @RequestBody UserUpdateBody body)
    {
        userService.updateUser(body);
        return R.ok();
    }

    /**
     * 删除用户
     */
    @ApiLog (module = "系统管理", tag = "删除用户")
    @RequiresPermissions ("sys:user:delete")
    @PostMapping ("delete")
    public R deleteUser(@Validated @RequestBody DefaultOpByIdBody body)
    {
        userService.deleteUser(body.getId());
        return R.ok();
    }

    /**
     * 更新用户密码
     */
    @ApiLog (module = "系统管理", tag = "更新用户登录密码")
    @PostMapping ("update_password")
    public R updateUserPassword(@Validated @RequestBody UserPasswordUpdateBody body)
    {
        userService.updateLoginPassword(body);
        return R.ok();
    }

    /**
     * 重置用户密码
     */
    @ApiLog (module = "系统管理", tag = "重置用户登录密码")
    @RequiresPermissions ("sys:user:reset_pass")
    @PostMapping ("reset_password")
    public R resetUserPassword(@Validated @RequestBody UserPasswordResetBody body)
    {
        userService.resetLoginPassword(body.getUserId());
        return R.ok();
    }

    /**
     * 获取用户信息
     */
    @RequiresPermissions (value = "sys:user:view")
    @GetMapping ("info")
    public R getUserInfo(@Validated DefaultInfoQuery query)
    {
        return R.ok(userService.getUserInfo(query.getId()));
    }

    /**
     * 获取用户列表
     */
    @GetMapping ("list")
    public R getUserList(@Validated UserListQuery query)
    {
        return R.ok(userService.listUser(query));
    }

    /**
     * 获取用户分页列表
     */
    @RequiresPermissions ("sys:user:page")
    @GetMapping ("page")
    public R getUserPage(@Validated UserPageQuery query)
    {
        return R.ok(userService.paginateUser(query));
    }
}
