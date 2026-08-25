package com.lee.tms.modules.system.service.impl;

import com.lee.tms.infrastructure.auth.AuthContext;
import com.lee.tms.modules.system.dto.vo.FunctionMenuVo;
import com.lee.tms.modules.system.service.FunctionService;
import com.lee.tms.modules.system.service.PermissionService;
import com.lee.tms.modules.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PermissionServiceImpl implements PermissionService
{
    @Autowired
    private RoleService roleService;

    @Autowired
    private FunctionService functionService;

    @Override
    public Set<String> getUserRoleKeys(Long userId)
    {
        Set<String> roles = new HashSet<>();
        if (AuthContext.isRoot(userId))
        {
            roles.add("admin");
        }
        else
        {
            roles.addAll(roleService.listRoleKeysByUserId(userId));
        }
        return roles;
    }

    @Override
    public Set<String> getUserFunctionKeys(Long userId)
    {
        Set<String> functions = new HashSet<>();
        if (AuthContext.isRoot(userId))
        {
            functions.add("*:*:*");
        }
        else
        {
            functions.addAll(functionService.listFunctionKeysByUserId(userId));
        }
        return functions;
    }

    @Override
    public List<FunctionMenuVo> getUserFunctionMenus(Long userId)
    {
        List<FunctionMenuVo> menus;
        if (AuthContext.isRoot(userId))
        {
            menus = functionService.getAllFunctionMenusTree();
        }
        else
        {
            menus = functionService.getFunctionMenusTreeByUserId(userId);
        }
        return menus;
    }
}
