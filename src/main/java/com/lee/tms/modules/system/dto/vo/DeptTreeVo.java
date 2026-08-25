package com.lee.tms.modules.system.dto.vo;

import cn.hutool.core.bean.BeanUtil;
import com.lee.tms.infrastructure.rest.vo.TreeVo;
import com.lee.tms.modules.system.entity.Dept;

public final class DeptTreeVo extends TreeVo<DeptTreeVo, Long>
{
    private String deptCode;

    private String deptName;

    public static DeptTreeVo fromModel(Dept dept)
    {
        DeptTreeVo vo = new DeptTreeVo();
        BeanUtil.copyProperties(dept, vo);
        return vo;
    }

    public String getDeptCode()
    {
        return deptCode;
    }

    public void setDeptCode(String deptCode)
    {
        this.deptCode = deptCode;
    }

    public String getDeptName()
    {
        return deptName;
    }

    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }
}
