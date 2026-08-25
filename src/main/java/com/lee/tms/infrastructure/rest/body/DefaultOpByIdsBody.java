package com.lee.tms.infrastructure.rest.body;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class DefaultOpByIdsBody
{
    @NotNull (message = "参数 [ids] 不能为空")
    @Size (min = 1, message = "参数 [ids] 至少包含一个元素")
    private List<Long> ids;
}
