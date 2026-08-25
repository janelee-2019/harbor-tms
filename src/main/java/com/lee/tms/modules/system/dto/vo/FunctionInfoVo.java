package com.lee.tms.modules.system.dto.vo;

import cn.hutool.core.bean.BeanUtil;
import com.lee.tms.modules.system.entity.Function;
import lombok.Data;

@Data
public final class FunctionInfoVo
{
    private Long id;

    private String functionKey;

    private String functionName;

    private Long parentId;

    private Long functionSort;

    private String functionType;

    private String remark;

    private Long functionLevel;

    private String vuePath;

    private String vueComponent;

    private String vueRedirect;

    private String vueName;

    private String vueIcon;

    private String vueAffix;

    public static FunctionInfoVo fromModel(Function function)
    {
        FunctionInfoVo vo = new FunctionInfoVo();
        BeanUtil.copyProperties(function, vo);
        return vo;
    }
}
