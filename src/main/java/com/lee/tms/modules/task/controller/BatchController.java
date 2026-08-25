package com.lee.tms.modules.task.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.tms.infrastructure.log.ApiLog;
import com.lee.tms.infrastructure.rest.R;
import com.lee.tms.infrastructure.rest.body.DefaultOpByIdsBody;
import com.lee.tms.infrastructure.rest.query.DefaultInfoQuery;
import com.lee.tms.infrastructure.rest.vo.DefaultPageVo;
import com.lee.tms.modules.task.dto.body.BatchAddBody;
import com.lee.tms.modules.task.dto.body.BatchUpdateBody;
import com.lee.tms.modules.task.dto.query.BatchPageQuery;
import com.lee.tms.modules.task.dto.vo.BatchListItemVo;
import com.lee.tms.modules.task.dto.vo.BatchVo;
import com.lee.tms.modules.task.entity.Batch;
import com.lee.tms.modules.task.service.BatchService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/**
 * 批次管理
 *
 * @author lee
 */
@RequiredArgsConstructor
@RestController
@RequestMapping ("/task/batch")
public class BatchController
{
    private final BatchService batchService;

    /**
     * 添加批次
     */
    @RequiresPermissions ("task:batch:add")
    @ApiLog (module = "", tag = "新增Batch")
    @PostMapping ("add")
    public R insertBatch(@Validated @RequestBody BatchAddBody body)
    {
        batchService.insertBatch(body);
        return R.ok();
    }

    /**
     * 更新批次
     */
    @RequiresPermissions ("task:batch:update")
    @ApiLog (module = "", tag = "更新Batch")
    @PostMapping ("update")
    public R updateBatch(@Validated @RequestBody BatchUpdateBody body)
    {
        batchService.updateBatch(body);
        return R.ok();
    }

    /**
     * 删除批次
     */
    @RequiresPermissions ("task:batch:delete")
    @ApiLog (module = "", tag = "删除Batch")
    @PostMapping ("delete")
    public R deleteBatch(@Validated @RequestBody DefaultOpByIdsBody body)
    {
        batchService.deleteBatch(body.getIds());
        return R.ok();
    }

    /**
     * 获取批次详情
     */
    @RequiresPermissions ("task:batch:info")
    @GetMapping ("info")
    public R<BatchVo> getBatchInfo(@Validated DefaultInfoQuery query)
    {
        return R.ok(BatchVo.fromModel(batchService.getDetail(query.getId())));
    }

    /**
     * 获取批次分页列表
     */
    @RequiresPermissions ("task:batch:page")
    @GetMapping ("page")
    public R<DefaultPageVo<BatchListItemVo>> getBatchsPage(@Validated BatchPageQuery query)
    {
        IPage<Batch> page = batchService.getPage(query);
        DefaultPageVo<BatchListItemVo> vos = new DefaultPageVo<>(query.getPageNo(), query.getPageSize(), page.getTotal(),
                                                                 page.getRecords().stream().map(BatchListItemVo::fromModel).collect(Collectors.toList()));
        return R.ok(vos);
    }
}
