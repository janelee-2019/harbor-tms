package com.lee.tms.modules.task.dto.query;

import com.lee.tms.infrastructure.rest.query.DefaultPageQuery;
import lombok.Data;

@Data
public class ProjectSummaryPageQuery extends DefaultPageQuery
{
    /**
     * 服务发起公司
     */
    private String affiliateId;

    /**
     * 查询关键字，支持文本字段的模糊查询
     */
    private String keyword;
}