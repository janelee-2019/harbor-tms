package com.lee.tms.modules.system.dto.query;

import lombok.Data;

@Data
public final class DeptTreeQuery
{
    private String deptCode;

    private String deptName;

    private Boolean includeParent = false;
}
