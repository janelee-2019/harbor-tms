package com.lee.tms.modules.system.dto.body;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public final class UserPasswordUpdateBody
{
    @NotBlank (message = "参数 [loginPassword] 不能为空")
    private String loginPassword;
}
