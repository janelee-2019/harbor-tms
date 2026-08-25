package com.lee.tms.modules.system.dto.body;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FunctionAddBody
{
    private String functionKey;

    @NotBlank (message = "参数 [functionName] 不能为空")
    private String functionName;

    @NotNull (message = "参数 [parentId] 不能为空")
    private Long parentId;

    private Long functionSort;

    @NotBlank (message = "参数 [functionType] 不能为空")
    private String functionType;

    private String remark;

    private Long functionLevel;

    private String vuePath;

    private String vueComponent;

    private String vueRedirect;

    private String vueName;

    private String vueIcon;

    private String vueAffix;
}
