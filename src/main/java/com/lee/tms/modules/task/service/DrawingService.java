package com.lee.tms.modules.task.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.tms.modules.task.dto.body.DrawingAddBody;
import com.lee.tms.modules.task.dto.body.DrawingUpdateBody;
import com.lee.tms.modules.task.dto.query.DrawingPageQuery;
import com.lee.tms.modules.task.entity.Drawing;

import java.util.List;

/**
 * 图纸信息服务
 */
public interface DrawingService extends IService<Drawing>
{
    void insertDrawing(DrawingAddBody body);

    void updateDrawing(DrawingUpdateBody body);

    void deleteDrawing(List<Long> ids);


    Drawing getDetail(Long id);

    IPage<Drawing> getPage(DrawingPageQuery query);
}