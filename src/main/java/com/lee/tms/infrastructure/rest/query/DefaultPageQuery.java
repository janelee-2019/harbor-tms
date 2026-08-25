package com.lee.tms.infrastructure.rest.query;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DefaultPageQuery
{
    /**
     * 页号
     */
    @NotNull (message = "查询参数 [pageNo] 不能为空")
    @Min (value = 1, message = "查询参数 [pageNo] 必须 >= 1")
    private Integer pageNo;
    /**
     * 分页尺寸
     */
    @NotNull (message = "查询参数 [pageSize] 不能为空")
    @Min (value = 1, message = "查询参数 [pageSize] 必须 >= 1")
    @Max (value = 100, message = "查询参数 [pageSize] 必须 <=100")
    private Integer pageSize;
}
