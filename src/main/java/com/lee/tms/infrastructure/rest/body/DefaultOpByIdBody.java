package com.lee.tms.infrastructure.rest.body;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public final class DefaultOpByIdBody
{
    @NotNull (message = "参数 [id] 不能为空")
    private Long id;
}
