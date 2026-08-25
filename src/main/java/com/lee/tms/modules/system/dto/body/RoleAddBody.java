package com.lee.tms.modules.system.dto.body;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class RoleAddBody
{
    @NotBlank (message = "参数 [roleKey] 不能为空")
    private String roleKey;

    @NotBlank (message = "参数 [roleName] 不能为空")
    private String roleName;

    private Long roleLevel;

    private String remark;

    @NotNull (message = "参数 [functionIds] 不能为空")
    @Size (min = 1, message = "参数 [functionIds] 必须至少包含一个元素")
    private List<Long> functionIds;
}
