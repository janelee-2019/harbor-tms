package com.lee.tms.modules.system.dto.body;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeptAddBody
{
    private String deptCode;

    @NotBlank (message = "参数 [deptName] 不能为空")
    private String deptName;

    @NotNull (message = "参数 [parentId] 不能为空")
    private Long parentId;

    private Long deptLevel;

    private String remark;

    @NotNull (message = "参数 [companyId] 不能为空")
    private Long companyId;
}
