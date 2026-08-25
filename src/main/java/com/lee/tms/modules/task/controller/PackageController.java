package com.lee.tms.modules.task.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.tms.infrastructure.log.ApiLog;
import com.lee.tms.infrastructure.rest.R;
import com.lee.tms.infrastructure.rest.body.DefaultOpByIdsBody;
import com.lee.tms.infrastructure.rest.query.DefaultInfoQuery;
import com.lee.tms.infrastructure.rest.vo.DefaultPageVo;
import com.lee.tms.modules.task.dto.body.PackageAddBody;
import com.lee.tms.modules.task.dto.body.PackageUpdateBody;
import com.lee.tms.modules.task.dto.query.PackagePageQuery;
import com.lee.tms.modules.task.dto.vo.PackageListItemVo;
import com.lee.tms.modules.task.dto.vo.PackageVo;
import com.lee.tms.modules.task.entity.Package;
import com.lee.tms.modules.task.service.PackageService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/**
 * 分包管理
 *
 * @author lee
 */
@RequiredArgsConstructor
@RestController
@RequestMapping ("/task/package")
public class PackageController
{
    private final PackageService packageService;

    /**
     * 添加分包
     */
    @RequiresPermissions ("task:package:add")
    @ApiLog (module = "", tag = "新增Package")
    @PostMapping ("add")
    public R insertPackage(@Validated @RequestBody PackageAddBody body)
    {
        packageService.insertPackage(body);
        return R.ok();
    }

    /**
     * 更新分包
     */
    @RequiresPermissions ("task:package:update")
    @ApiLog (module = "", tag = "更新Package")
    @PostMapping ("update")
    public R updatePackage(@Validated @RequestBody PackageUpdateBody body)
    {
        packageService.updatePackage(body);
        return R.ok();
    }

    /**
     * 删除分包
     */
    @RequiresPermissions ("task:package:delete")
    @ApiLog (module = "", tag = "删除Package")
    @PostMapping ("delete")
    public R deletePackage(@Validated @RequestBody DefaultOpByIdsBody body)
    {
        packageService.deletePackage(body.getIds());
        return R.ok();
    }

    /**
     * 获取分包详情
     */
    @RequiresPermissions ("task:package:info")
    @GetMapping ("info")
    public R<PackageVo> getPackageInfo(@Validated DefaultInfoQuery query)
    {
        return R.ok(PackageVo.fromModel(packageService.getDetail(query.getId())));
    }

    /**
     * 获取分包分页列表
     */
    @RequiresPermissions ("task:package:page")
    @GetMapping ("page")
    public R<DefaultPageVo<PackageListItemVo>> getPackagesPage(@Validated PackagePageQuery query)
    {
        IPage<Package> page = packageService.getPage(query);
        DefaultPageVo<PackageListItemVo> vos = new DefaultPageVo<>(query.getPageNo(), query.getPageSize(), page.getTotal(),
                                                                   page.getRecords().stream().map(PackageListItemVo::fromModel).collect(Collectors.toList()));
        return R.ok(vos);
    }
}
