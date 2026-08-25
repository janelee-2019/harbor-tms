package com.lee.tms.modules.system.dto.vo;

import cn.hutool.core.bean.BeanUtil;
import com.lee.tms.modules.system.entity.Role;
import com.lee.tms.modules.system.entity.User;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public final class UserInfoVo
{
    private Long id;

    private String loginName;

    private String remark;

    private String employeeCode;

    private String employeeName;

    private String employeeTel;

    private String employeeEmail;

    private String employeeAddress;

    private String employeePicture;

    private String employeeSex;

    private Long deptId;

    private List<Long> roleIds;

    public static UserInfoVo fromModel(User user)
    {
        UserInfoVo vo = new UserInfoVo();
        BeanUtil.copyProperties(user, vo);
        vo.roleIds = user.getRoles().stream().map(Role::getId).collect(Collectors.toList());
        return vo;
    }
}
