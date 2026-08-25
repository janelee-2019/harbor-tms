package com.lee.tms.modules.system.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.lee.tms.modules.system.entity.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 部门信息表 Mapper 接口
 * </p>
 */
public interface DeptMapper extends BaseMapper<Dept>
{
    IPage<Dept> selectDeptPage(IPage<Dept> page, @Param (Constants.WRAPPER) Wrapper<Dept> queryWrapper);

    List<Dept> selectChildDepts(Long deptId);
}
