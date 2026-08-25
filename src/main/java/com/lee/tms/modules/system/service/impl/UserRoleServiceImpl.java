package com.lee.tms.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.tms.modules.system.entity.UserRole;
import com.lee.tms.modules.system.mapper.UserRoleMapper;
import com.lee.tms.modules.system.service.UserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService
{}
