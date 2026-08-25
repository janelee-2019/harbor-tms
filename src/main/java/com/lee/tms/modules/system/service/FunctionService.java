package com.lee.tms.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.tms.modules.system.dto.body.FunctionAddBody;
import com.lee.tms.modules.system.dto.body.FunctionUpdateBody;
import com.lee.tms.modules.system.dto.vo.FunctionMenuVo;
import com.lee.tms.modules.system.dto.vo.FunctionTreeVo;
import com.lee.tms.modules.system.entity.Function;

import java.util.List;
import java.util.Set;

public interface FunctionService extends IService<Function>
{
    Set<String> listFunctionKeysByUserId(Long userId);

    List<Function> listFunctionsByRoleId(Long roleId, boolean strictly);

    List<FunctionTreeVo> getAllFunctionsTree();

    List<FunctionTreeVo> getFunctionTreeByRoleId(Long roleId);

    List<FunctionMenuVo> getAllFunctionMenusTree();

    List<FunctionMenuVo> getFunctionMenusTreeByUserId(Long userId);

    void addFunction(FunctionAddBody body);

    void updateFunction(FunctionUpdateBody body);

    void deleteFunction(Long functionId);
}
