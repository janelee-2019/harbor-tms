package com.lee.tms.modules.task.service.impl;

import cn.hutool.core.bean.BeanUtil;import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.tms.infrastructure.auth.AuthContext;
import com.lee.tms.infrastructure.rest.RestCode;
import com.lee.tms.infrastructure.rest.RestException;
import com.lee.tms.modules.task.dto.body.BatchAddBody;
import com.lee.tms.modules.task.dto.body.BatchUpdateBody;
import com.lee.tms.modules.task.dto.query.BatchQuery;
import com.lee.tms.modules.task.dto.query.BatchPageQuery;
import com.lee.tms.modules.task.entity.Batch;
import com.lee.tms.modules.task.mapper.BatchMapper;
import com.lee.tms.modules.task.service.BatchService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BatchServiceImpl extends ServiceImpl<BatchMapper, Batch> implements BatchService
{
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertBatch(BatchAddBody body)
    {
        Batch entity = new Batch();
        BeanUtil.copyProperties(body, entity);
        entity.setCreateBy(AuthContext.getCurrentUserId());
        save(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateBatch(BatchUpdateBody body)
    {
        Batch entity = getDetail(body.getId());
        BeanUtil.copyProperties(body, entity);
        entity.setUpdateBy(AuthContext.getCurrentUserId());
        updateById(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteBatch(List<Long> ids)
    {
        removeBatchByIds(ids);
    }

    @Override
    public Batch getDetail(Long id)
    {
        Batch entity = getById(id);
        if (entity == null)
        {
            throw new RestException(RestCode.SYS_ERR, "批次信息表不存在 [ID = {}]", id);
        }
        return entity;
    }

    @Override
    public IPage<Batch> getPage(BatchPageQuery query)
    {
        IPage<Batch> page = new Page<>(query.getPageNo(), query.getPageSize());
        LambdaQueryWrapper<Batch> wrapper = new LambdaQueryWrapper<>();

        if (StrUtil.isNotBlank(query.getKeyword()))
        {
            wrapper.and(w ->
                    w.like(Batch::getSn, query.getKeyword())
                    .or().like(Batch::getPackageNo, query.getKeyword())
                    .or().like(Batch::getBatchNo, query.getKeyword())
                    .or().like(Batch::getQcRating, query.getKeyword())
                    .or().like(Batch::getRemark, query.getKeyword())
                    .or().like(Batch::getIsIssued, query.getKeyword())
                    .or().like(Batch::getIsDistributed, query.getKeyword())
                    .or().like(Batch::getIsRated, query.getKeyword())
                    );
        }
        return baseMapper.selectPage(page, wrapper);
    }
}