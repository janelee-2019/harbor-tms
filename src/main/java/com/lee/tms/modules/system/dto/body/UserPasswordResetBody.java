package com.lee.tms.modules.system.dto.body;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public final class UserPasswordResetBody
{
    @NotNull (message = "参数 [userId] 不能为空")
    private Long userId;
}
