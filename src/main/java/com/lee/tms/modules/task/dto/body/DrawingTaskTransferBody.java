package com.lee.tms.modules.task.dto.body;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public final class DrawingTaskTransferBody
{
    @NotNull (message = "参数 [ids] 不能为空")
    private List<Long> ids;

    @NotNull (message = "参数 [userId] 不能为空")
    private Long userId;

    private String reason;
}
