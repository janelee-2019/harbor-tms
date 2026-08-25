package com.lee.tms.modules.system.dto.vo;

import cn.hutool.core.bean.BeanUtil;
import com.lee.tms.modules.system.entity.Dept;
import lombok.Data;

@Data
public final class DeptListItemVo
{
    private Long id;

    private String deptCode;

    private String deptName;

    private Long deptLevel;

    private String companyShortName;

    public static DeptListItemVo fromModel(Dept dept)
    {
        DeptListItemVo vo = new DeptListItemVo();
        BeanUtil.copyProperties(dept, vo);
        vo.companyShortName = dept.getCompany().getCompanyShortName();
        return vo;
    }
}
