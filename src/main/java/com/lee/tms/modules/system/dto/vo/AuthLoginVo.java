package com.lee.tms.modules.system.dto.vo;

import cn.hutool.core.bean.BeanUtil;
import com.lee.tms.modules.system.entity.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public final class AuthLoginVo
{
    private Long id;

    private String loginName;

    private String lastLoginIp;

    private LocalDateTime lastLoginDate;

    private String employeeCode;

    private String employeeName;

    private String employeeEmail;

    private String employeeAddress;

    private String employeePicture;

    private String employeeSex;

    private Set<String> roles;

    private Set<String> perms;

    private List<FunctionMenuVo> menus;

    private String token;

    public static AuthLoginVo fromModel(User user)
    {
        AuthLoginVo vo = new AuthLoginVo();
        BeanUtil.copyProperties(user, vo);
        return vo;
    }
}
