package com.lee.tms.modules.system.service;

import com.lee.tms.modules.system.dto.vo.FunctionMenuVo;

import java.util.List;
import java.util.Set;

public interface PermissionService
{
    Set<String> getUserRoleKeys(Long userId);

    Set<String> getUserFunctionKeys(Long userId);

    List<FunctionMenuVo> getUserFunctionMenus(Long userId);
}
