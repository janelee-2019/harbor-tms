package com.lee.tms.modules.system.dto.query;

import com.lee.tms.infrastructure.rest.query.DefaultPageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode (callSuper = true)
@Data
public final class UserPageQuery extends DefaultPageQuery
{
    private String loginName;

    private String employeeName;
}
