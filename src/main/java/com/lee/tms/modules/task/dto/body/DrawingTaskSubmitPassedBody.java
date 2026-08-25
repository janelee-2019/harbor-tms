package com.lee.tms.modules.task.dto.body;

import com.lee.tms.infrastructure.rest.body.DefaultOpByIdsBody;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode (callSuper = true)
@Data
public class DrawingTaskSubmitPassedBody extends DefaultOpByIdsBody
{
    @NotNull (message = "参数 [isPassed] 不能为空")
    private Boolean isPassed;

    private String reason;
}
