package com.lee.tms.modules.system.dto.vo;

import cn.hutool.core.bean.BeanUtil;
import com.lee.tms.modules.system.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public final class RoleInfoVo
{
    private Long id;

    private String roleKey;

    private String roleName;

    private Long roleLevel;

    private String remark;

    private List<Long> functions;

    public static RoleInfoVo fromModel(Role role)
    {
        RoleInfoVo vo = new RoleInfoVo();
        BeanUtil.copyProperties(role, vo);
        return vo;
    }
}
