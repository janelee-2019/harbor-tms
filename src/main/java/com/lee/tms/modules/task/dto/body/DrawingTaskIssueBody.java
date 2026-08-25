package com.lee.tms.modules.task.dto.body;

import com.lee.tms.infrastructure.rest.body.DefaultOpByIdsBody;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public final class DrawingTaskIssueBody extends DefaultOpByIdsBody
{

    @NotNull (message = "参数 [userId] 不能为空")
    private Long userId;
}
