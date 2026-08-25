package com.lee.tms.infrastructure.rest.query;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public final class DefaultInfoQuery
{
    @NotNull (message = "查询参数 [id] 不能为空")
    private Long id;
}
