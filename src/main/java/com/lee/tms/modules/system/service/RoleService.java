package com.lee.tms.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.tms.modules.system.dto.body.RoleAddBody;
import com.lee.tms.modules.system.dto.body.RoleUpdateBody;
import com.lee.tms.modules.system.dto.vo.RoleInfoVo;
import com.lee.tms.modules.system.entity.Role;

import java.util.Set;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 */
public interface RoleService extends IService<Role>
{
    Set<String> listRoleKeysByUserId(Long userId);

    RoleInfoVo getRoleInfo(Long roleId);

    void addRole(RoleAddBody body);

    void updateRole(RoleUpdateBody body);

    void deleteRole(Long roleId);
}
