package com.lee.tms.modules.system.dto.body;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public final class UserUpdateBody
{
    @NotNull (message = "参数 [id] 不能为空")
    private Long id;

    private String remark;

    private String employeeCode;

    @NotBlank (message = "参数 [employeeName] 不能为空")
    private String employeeName;

    private String employeeTel;

    private String employeeEmail;

    private String employeeAddress;

    private String employeePicture;

    private String employeeSex;

    @NotNull (message = "参数 [deptId] 不能为空")
    private Long deptId;

    @NotNull (message = "参数 [roleIds] 不能为空")
    @Size (min = 1, message = "参数 [roleIds] 必须至少包含一个元素")
    private List<Long> roleIds;
}
