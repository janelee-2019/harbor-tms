package com.lee.tms.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.tms.modules.system.entity.RoleFunction;
import com.lee.tms.modules.system.mapper.RoleFunctionMapper;
import com.lee.tms.modules.system.service.RoleFunctionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色与功能关联表 服务实现类
 * </p>
 */
@Service
public class RoleFunctionServiceImpl extends ServiceImpl<RoleFunctionMapper, RoleFunction> implements RoleFunctionService
{

}
