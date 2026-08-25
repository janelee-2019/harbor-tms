package com.lee.tms.modules.task.dto.query;

import com.lee.tms.infrastructure.rest.query.DefaultPageQuery;
import lombok.Data;

@Data
public class BatchPageQuery extends DefaultPageQuery
{
    /**
     * 服务请求 ID
     */
    private String srId;
    /**
     * 包 ID
     */
    private String packageId;
    /**
     * 小组 ID
     */
    private String groupId;
    /**
     * 下发者
     */
    private String issuerId;
    /**
     * 质控员
     */
    private String qcId;

    /**
     * 查询关键字，支持文本字段的模糊查询
     */
    private String keyword;
}