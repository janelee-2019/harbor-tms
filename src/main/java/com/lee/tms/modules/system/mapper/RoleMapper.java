package com.lee.tms.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lee.tms.modules.system.entity.Role;

import java.util.List;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 */
public interface RoleMapper extends BaseMapper<Role>
{
    List<Role> selectRolesByUserId(Long userId);
}
