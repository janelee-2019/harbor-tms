package com.lee.tms.infrastructure.rest.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class DefaultPageVo<T>
{
    /**
     * 当前页码
     */
    private Integer pageNo;
    /**
     * 每页记录数
     */
    private Integer pageSize;
    /**
     * 总记录数
     */
    private Long total;

    /**
     * 返回的记录列表
     */
    private List<T> records;

    public DefaultPageVo(Integer pageNo, Integer pageSize, Long total, List<T> records)
    {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.total = total;
        this.records = records;
    }
}
