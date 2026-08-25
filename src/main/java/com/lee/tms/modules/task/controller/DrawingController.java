package com.lee.tms.modules.task.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.tms.infrastructure.log.ApiLog;
import com.lee.tms.infrastructure.rest.R;
import com.lee.tms.infrastructure.rest.body.DefaultOpByIdsBody;
import com.lee.tms.infrastructure.rest.query.DefaultInfoQuery;
import com.lee.tms.infrastructure.rest.vo.DefaultPageVo;
import com.lee.tms.modules.task.dto.body.DrawingAddBody;
import com.lee.tms.modules.task.dto.body.DrawingUpdateBody;
import com.lee.tms.modules.task.dto.query.DrawingPageQuery;
import com.lee.tms.modules.task.dto.vo.DrawingListItemVo;
import com.lee.tms.modules.task.dto.vo.DrawingVo;
import com.lee.tms.modules.task.entity.Drawing;
import com.lee.tms.modules.task.service.DrawingService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/**
 * 图纸管理
 *
 * @author lee
 */
@RequiredArgsConstructor
@RestController
@RequestMapping ("/task/drawing")
public class DrawingController
{
    private final DrawingService drawingService;

    /**
     * 添加图纸
     */
    @RequiresPermissions ("task:drawing:add")
    @ApiLog (module = "", tag = "新增Drawing")
    @PostMapping ("add")
    public R insertDrawing(@Validated @RequestBody DrawingAddBody body)
    {
        drawingService.insertDrawing(body);
        return R.ok();
    }

    /**
     * 更新图纸
     */
    @RequiresPermissions ("task:drawing:update")
    @ApiLog (module = "", tag = "更新Drawing")
    @PostMapping ("update")
    public R updateDrawing(@Validated @RequestBody DrawingUpdateBody body)
    {
        drawingService.updateDrawing(body);
        return R.ok();
    }

    /**
     * 删除图纸
     */
    @RequiresPermissions ("task:drawing:delete")
    @ApiLog (module = "", tag = "删除Drawing")
    @PostMapping ("delete")
    public R deleteDrawing(@Validated @RequestBody DefaultOpByIdsBody body)
    {
        drawingService.deleteDrawing(body.getIds());
        return R.ok();
    }

    /**
     * 获取图纸详情
     */
    @RequiresPermissions ("task:drawing:info")
    @GetMapping ("info")
    public R<DrawingVo> getDrawingInfo(@Validated DefaultInfoQuery query)
    {
        return R.ok(DrawingVo.fromModel(drawingService.getDetail(query.getId())));
    }

    /**
     * 获取图纸分页列表
     */
    @RequiresPermissions ("task:drawing:page")
    @GetMapping ("page")
    public R<DefaultPageVo<DrawingListItemVo>> getDrawingsPage(@Validated DrawingPageQuery query)
    {
        IPage<Drawing> page = drawingService.getPage(query);
        DefaultPageVo<DrawingListItemVo> vos = new DefaultPageVo<>(query.getPageNo(), query.getPageSize(), page.getTotal(),
                                                                   page.getRecords().stream().map(DrawingListItemVo::fromModel).collect(Collectors.toList()));
        return R.ok(vos);
    }
}
