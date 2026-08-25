package com.lee.tms.modules.system.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.lee.tms.infrastructure.rest.query.DefaultPageQuery;
import com.lee.tms.modules.system.entity.Role;
import com.lee.tms.modules.system.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User>
{
    List<User> selectUserList(@Param ("userEw") Wrapper<User> userQueryWrapper, @Param ("roleEw") Wrapper<Role> roleQueryWrapper);

    User selectUser(@Param (Constants.WRAPPER) Wrapper<User> queryWrapper);

    List<User> selectAllUserList();

    IPage<User> selectUserPage(IPage page, @Param (Constants.WRAPPER) Wrapper<User> queryWrapper);

    User selectUserById(Long userId);
}
