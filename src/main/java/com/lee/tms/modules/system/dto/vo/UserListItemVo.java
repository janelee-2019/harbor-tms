package com.lee.tms.modules.system.dto.vo;

import cn.hutool.core.bean.BeanUtil;
import com.lee.tms.modules.system.entity.User;
import lombok.Data;

@Data
public final class UserListItemVo
{
    private Long id;

    private String loginName;

    private String employeeCode;

    private String employeeName;

    private String employeeTel;

    private String employeeSex;

    private String deptName;

    private String companyName;

    private String companyShortName;

    public static UserListItemVo fromModel(User user)
    {
        UserListItemVo vo = new UserListItemVo();
        BeanUtil.copyProperties(user, vo);
        vo.deptName = user.getDept().getDeptName();
        vo.companyName = user.getCompany().getCompanyName();
        vo.companyShortName = user.getCompany().getCompanyShortName();
        return vo;
    }
}
