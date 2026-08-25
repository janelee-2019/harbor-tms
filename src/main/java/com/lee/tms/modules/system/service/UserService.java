package com.lee.tms.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.tms.infrastructure.rest.vo.DefaultPageVo;
import com.lee.tms.modules.system.dto.body.UserAddBody;
import com.lee.tms.modules.system.dto.body.UserPasswordUpdateBody;
import com.lee.tms.modules.system.dto.body.UserUpdateBody;
import com.lee.tms.modules.system.dto.query.UserListQuery;
import com.lee.tms.modules.system.dto.query.UserPageQuery;
import com.lee.tms.modules.system.dto.vo.UserInfoVo;
import com.lee.tms.modules.system.dto.vo.UserSimpleVo;
import com.lee.tms.modules.system.entity.User;

import java.util.List;

public interface UserService extends IService<User>
{
    void addUser(UserAddBody body);

    void updateUser(UserUpdateBody body);

    void deleteUser(Long userId);

    List<UserSimpleVo> listUser(UserListQuery query);

    List<User> getAllUsers();

    DefaultPageVo<User> paginateUser(UserPageQuery query);

    UserInfoVo getUserInfo(Long userId);

    void updateLoginPassword(UserPasswordUpdateBody body);

    void resetLoginPassword(Long userId);
}
