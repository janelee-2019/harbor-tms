package com.lee.tms.modules.system.dto.vo;

import cn.hutool.core.bean.BeanUtil;
import com.lee.tms.modules.system.entity.User;
import lombok.Data;

@Data
public final class UserSimpleVo
{
    private Long id;

    private String employeeName;

    public static UserSimpleVo fromModel(User user)
    {
        UserSimpleVo vo = new UserSimpleVo();
        BeanUtil.copyProperties(user, vo);
        return vo;
    }
}
