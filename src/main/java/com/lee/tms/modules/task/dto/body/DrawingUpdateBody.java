package com.lee.tms.modules.task.dto.body;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DrawingUpdateBody extends DrawingAddBody
{
    @NotNull (message = "参数 [id] 不能为空")
    protected Long id;
}