package com.lee.tms.modules.task.dto.vo;

import cn.hutool.core.bean.BeanUtil;
import com.lee.tms.modules.task.entity.Package;
import lombok.Data;

@Data
public final class PackageVo extends PackageListItemVo
{
    public static PackageVo fromModel(Package e)
    {
        PackageVo vo = new PackageVo();
        BeanUtil.copyProperties(e, vo);
        return vo;
    }
}