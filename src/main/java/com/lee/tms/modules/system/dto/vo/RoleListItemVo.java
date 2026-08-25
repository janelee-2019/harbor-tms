package com.lee.tms.modules.system.dto.vo;

import cn.hutool.core.bean.BeanUtil;
import com.lee.tms.modules.system.entity.Role;
import lombok.Data;

@Data
public final class RoleListItemVo
{
    private Long id;

    private String roleKey;

    private String roleName;

    private Long roleLevel;

    private String remark;

    public static RoleListItemVo fromModel(Role role)
    {
        RoleListItemVo vo = new RoleListItemVo();
        BeanUtil.copyProperties(role, vo);
        return vo;
    }
}
