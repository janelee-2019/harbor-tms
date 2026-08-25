package com.lee.tms.modules.task.dto.query;

import com.lee.tms.infrastructure.rest.query.DefaultPageQuery;
import lombok.Data;

@Data
public class PackagePageQuery extends DefaultPageQuery
{
    /**
     * 服务请求 ID
     */
    private String srId;
    /**
     * 负责团队 ID
     */
    private String teamId;
    /**
     * 下发者
     */
    private String issuerId;

    /**
     * 查询关键字，支持文本字段的模糊查询
     */
    private String keyword;
}