package com.lee.tms.modules.system.dto.vo;

import cn.hutool.core.bean.BeanUtil;
import com.lee.tms.modules.system.entity.Role;
import lombok.Data;

@Data
public final class RoleSimpleVo
{
    private Long id;

    private String roleName;

    public static RoleSimpleVo fromModel(Role role)
    {
        RoleSimpleVo vo = new RoleSimpleVo();
        BeanUtil.copyProperties(role, vo);
        return vo;
    }
}
