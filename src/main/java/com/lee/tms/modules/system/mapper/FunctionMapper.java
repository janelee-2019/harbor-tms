package com.lee.tms.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lee.tms.modules.system.entity.Function;

import java.util.List;

/**
 * <p>
 * 功能信息表 Mapper 接口
 * </p>
 */
public interface FunctionMapper extends BaseMapper<Function>
{
    List<String> selectFunctionKeysByUserId(Long userId);

    List<Function> selectChildFunctions(Long functionId);

    List<Function> selectAllFunctions();

    List<Function> selectAllFunctionMenus();

    List<Function> selectFunctionMenusByUserId(Long userId);

    List<Function> selectFunctionsByRoleId(Long roleId);

    List<Function> selectFunctionsStrictlyByRoleId(Long roleId);
}
