package com.lee.tms.modules.system.dto.body;

import cn.hutool.core.util.StrUtil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public final class UserAddBody
{
    @NotBlank (message = "参数 [loginName] 不能为空")
    private String loginName;

    @NotBlank (message = "参数 [loginPassword] 不能为空")
    private String loginPassword;

    private String remark;

    private String employeeCode;

    @NotBlank (message = "参数 [employeeName] 不能为空")
    private String employeeName;

    private String employeeTel;

    private String employeeEmail;

    private String employeeAddress;

    private String employeePicture;

    @NotBlank (message = "参数 [employeeSex] 不能为空")
    private String employeeSex;

    @NotNull (message = "参数 [deptId] 不能为空")
    private Long deptId;

    @NotNull (message = "参数 [roleIds] 不能为空")
    @Size (min = 1, message = "参数 [roleIds] 必须至少包含一个元素")
    private List<Long> roleIds;

    public String getEmployeeName()
    {
        employeeName = StrUtil.trim(employeeName);
        employeeName = StrUtil.cleanBlank(employeeName);
        employeeName = StrUtil.removeAllLineBreaks(employeeName);
        return employeeName;
    }
}
