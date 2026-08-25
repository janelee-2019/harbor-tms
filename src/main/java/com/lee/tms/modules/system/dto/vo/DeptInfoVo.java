package com.lee.tms.modules.system.dto.vo;

import cn.hutool.core.bean.BeanUtil;
import com.lee.tms.modules.system.entity.Dept;
import lombok.Data;

@Data
public final class DeptInfoVo
{
    private Long id;

    private String deptCode;

    private String deptName;

    private Long parentId;

    private Long deptLevel;

    private String remark;

    private Long companyId;

    public static DeptInfoVo fromModel(Dept dept)
    {
        DeptInfoVo vo = new DeptInfoVo();
        BeanUtil.copyProperties(dept, vo);
        return vo;
    }
}
