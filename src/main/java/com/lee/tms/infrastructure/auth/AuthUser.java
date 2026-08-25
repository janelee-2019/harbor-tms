package com.lee.tms.infrastructure.auth;

import cn.hutool.core.bean.BeanUtil;
import com.lee.tms.infrastructure.util.SpringContextUtil;
import com.lee.tms.modules.system.entity.Role;
import com.lee.tms.modules.system.entity.User;
import com.lee.tms.modules.system.service.UserService;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@Data
public final class AuthUser
{
    public static final long LEVEL_PROJECT_MANAGER = 30L;
    public static final long LEVEL_PROFESSIONAL_MANAGER = 40L;
    public static final long LEVEL_QC_MANAGER = 40L;
    public static final long LEVEL_GROUP_LEADER = 50L;
    public static final long LEVEL_ENGINEER = 60L;
    public static final long LEVEL_QC = 60L;

    private Long id;

    private String loginName;

    private String lastLoginIp;

    private LocalDateTime lastLoginDate;

    private String employeeCode;

    private String employeeName;

    private Long deptId;

    private Set<String> roleKeys;

    private Set<String> permKeys;

    private List<Role> roles;

    private Long minRoleLevel;

    public static AuthUser fromModel(User user)
    {
        AuthUser authUser = new AuthUser();
        BeanUtil.copyProperties(user, authUser);
        return authUser;
    }

    public User getUser()
    {
        UserService userService = SpringContextUtil.getBean(UserService.class);
        return userService.getById(id);
    }

    public boolean isRoot()
    {
        return id != null && id == 0L;
    }

    public boolean isEqualGroupLeader()
    {
        return minRoleLevel != null && minRoleLevel == LEVEL_GROUP_LEADER;
    }

    public boolean notLeader()
    {
        // role_level大于小组长角色的（级别越低 role_level越高）
        return minRoleLevel != null && minRoleLevel > LEVEL_GROUP_LEADER;
    }

    public boolean isJuniorOrEqualGroupLeader()
    {
        return minRoleLevel != null && minRoleLevel >= LEVEL_GROUP_LEADER;
    }

    public boolean isSuperiorThanGroupLeader()
    {
        return minRoleLevel != null && minRoleLevel < LEVEL_GROUP_LEADER;
    }
}
