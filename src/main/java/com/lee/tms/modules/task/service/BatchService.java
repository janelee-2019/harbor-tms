package com.lee.tms.modules.task.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.tms.modules.task.dto.body.BatchAddBody;
import com.lee.tms.modules.task.dto.body.BatchUpdateBody;
import com.lee.tms.modules.task.dto.query.BatchPageQuery;
import com.lee.tms.modules.task.entity.Batch;

import java.util.List;

/**
 * 批次信息服务
 */
public interface BatchService extends IService<Batch>
{
    void insertBatch(BatchAddBody body);

    void updateBatch(BatchUpdateBody body);

    void deleteBatch(List<Long> ids);


    Batch getDetail(Long id);

    IPage<Batch> getPage(BatchPageQuery query);
}