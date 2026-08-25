package com.lee.tms.modules.task.service.impl;

import cn.hutool.core.bean.BeanUtil;import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.tms.infrastructure.auth.AuthContext;
import com.lee.tms.infrastructure.rest.RestCode;
import com.lee.tms.infrastructure.rest.RestException;
import com.lee.tms.modules.task.dto.body.DrawingAddBody;
import com.lee.tms.modules.task.dto.body.DrawingUpdateBody;
import com.lee.tms.modules.task.dto.query.DrawingQuery;
import com.lee.tms.modules.task.dto.query.DrawingPageQuery;
import com.lee.tms.modules.task.entity.Drawing;
import com.lee.tms.modules.task.mapper.DrawingMapper;
import com.lee.tms.modules.task.service.DrawingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DrawingServiceImpl extends ServiceImpl<DrawingMapper, Drawing> implements DrawingService
{
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertDrawing(DrawingAddBody body)
    {
        Drawing entity = new Drawing();
        BeanUtil.copyProperties(body, entity);
        entity.setCreateBy(AuthContext.getCurrentUserId());
        save(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateDrawing(DrawingUpdateBody body)
    {
        Drawing entity = getDetail(body.getId());
        BeanUtil.copyProperties(body, entity);
        entity.setUpdateBy(AuthContext.getCurrentUserId());
        updateById(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteDrawing(List<Long> ids)
    {
        removeBatchByIds(ids);
    }

    @Override
    public Drawing getDetail(Long id)
    {
        Drawing entity = getById(id);
        if (entity == null)
        {
            throw new RestException(RestCode.SYS_ERR, "图纸信息表不存在 [ID = {}]", id);
        }
        return entity;
    }

    @Override
    public IPage<Drawing> getPage(DrawingPageQuery query)
    {
        IPage<Drawing> page = new Page<>(query.getPageNo(), query.getPageSize());
        LambdaQueryWrapper<Drawing> wrapper = new LambdaQueryWrapper<>();

        if (StrUtil.isNotBlank(query.getKeyword()))
        {
            wrapper.and(w ->
                    w.like(Drawing::getSn, query.getKeyword())
                    .or().like(Drawing::getPackageNo, query.getKeyword())
                    .or().like(Drawing::getBatchNo, query.getKeyword())
                    .or().like(Drawing::getDrawingFileName, query.getKeyword())
                    .or().like(Drawing::getDrawingNo, query.getKeyword())
                    .or().like(Drawing::getDrawingFileDir, query.getKeyword())
                    .or().like(Drawing::getDrawingCategory, query.getKeyword())
                    .or().like(Drawing::getDrawingType, query.getKeyword())
                    .or().like(Drawing::getDrawingStatus, query.getKeyword())
                    .or().like(Drawing::getRemark, query.getKeyword())
                    .or().like(Drawing::getDcNo, query.getKeyword())
                    .or().like(Drawing::getDrawingFilePath, query.getKeyword())
                    .or().like(Drawing::getProcInstId, query.getKeyword())
                    .or().like(Drawing::getQcRating, query.getKeyword())
                    );
        }
        return baseMapper.selectPage(page, wrapper);
    }
}