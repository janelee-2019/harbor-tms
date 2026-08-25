package com.lee.tms.modules.system.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.tms.infrastructure.rest.vo.DefaultPageVo;
import com.lee.tms.modules.system.dto.body.DeptAddBody;
import com.lee.tms.modules.system.dto.body.DeptUpdateBody;
import com.lee.tms.modules.system.dto.query.DeptPageQuery;
import com.lee.tms.modules.system.dto.query.DeptTreeQuery;
import com.lee.tms.modules.system.dto.vo.DeptTreeVo;
import com.lee.tms.modules.system.entity.Dept;

import java.util.List;

/**
 * <p>
 * 部门信息表 服务类
 * </p>
 */
public interface DeptService extends IService<Dept>
{

    DefaultPageVo<Dept> paginateDept(DeptPageQuery query);

    List<DeptTreeVo> getAllDeptTree();

    List<DeptTreeVo> getDeptTree(DeptTreeQuery query);

    void addDept(DeptAddBody body);

    void updateDept(DeptUpdateBody body);

    void deleteDept(Long deptId);

    Dept getDeptOrThrow(Wrapper<Dept> queryWrapper, String message, Object... params);

    List<Dept> listChildDept(Long parentDeptId);
}
