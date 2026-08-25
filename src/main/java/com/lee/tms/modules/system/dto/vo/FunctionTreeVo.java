package com.lee.tms.modules.system.dto.vo;

import cn.hutool.core.bean.BeanUtil;
import com.lee.tms.infrastructure.rest.vo.TreeVo;
import com.lee.tms.modules.system.entity.Function;

public final class FunctionTreeVo extends TreeVo<FunctionTreeVo, Long>
{
    private String functionKey;

    private String functionName;

    private String remark;

    public static FunctionTreeVo fromModel(Function function)
    {
        FunctionTreeVo vo = new FunctionTreeVo();
        BeanUtil.copyProperties(function, vo);
        return vo;
    }

    public String getFunctionKey()
    {
        return functionKey;
    }

    public void setFunctionKey(String functionKey)
    {
        this.functionKey = functionKey;
    }

    public String getFunctionName()
    {
        return functionName;
    }

    public void setFunctionName(String functionName)
    {
        this.functionName = functionName;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }
}
