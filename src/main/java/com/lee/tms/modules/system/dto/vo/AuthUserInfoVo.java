package com.lee.tms.modules.system.dto.vo;

import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lee.tms.infrastructure.auth.AuthUser;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class AuthUserInfoVo
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

    @JsonProperty ("roles")
    private Set<String> roleKeys;

    @JsonProperty ("perms")
    private Set<String> permKeys;

    private List<FunctionMenuVo> menus;

    public static AuthUserInfoVo fromAuthUser(AuthUser authUser)
    {
        AuthUserInfoVo vo = new AuthUserInfoVo();
        BeanUtil.copyProperties(authUser, vo);
        return vo;
    }
}
