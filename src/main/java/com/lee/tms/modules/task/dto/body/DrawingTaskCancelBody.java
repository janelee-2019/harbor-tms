package com.lee.tms.modules.task.dto.body;

import com.lee.tms.infrastructure.rest.body.DefaultOpByIdsBody;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class DrawingTaskCancelBody extends DefaultOpByIdsBody
{
    private String reason;
}
