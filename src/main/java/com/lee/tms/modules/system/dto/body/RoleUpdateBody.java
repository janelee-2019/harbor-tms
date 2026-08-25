package com.lee.tms.modules.system.dto.body;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode (callSuper = true)
@Data
public final class RoleUpdateBody extends RoleAddBody
{
    @NotNull (message = "参数 [id] 不能为空")
    private Long id;
}
