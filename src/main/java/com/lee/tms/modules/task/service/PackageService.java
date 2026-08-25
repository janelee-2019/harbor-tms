package com.lee.tms.modules.task.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.tms.modules.task.dto.body.PackageAddBody;
import com.lee.tms.modules.task.dto.body.PackageUpdateBody;
import com.lee.tms.modules.task.dto.query.PackagePageQuery;
import com.lee.tms.modules.task.entity.Package;

import java.util.List;

/**
 * 分包信息服务
 */
public interface PackageService extends IService<Package>
{
    void insertPackage(PackageAddBody body);

    void updatePackage(PackageUpdateBody body);

    void deletePackage(List<Long> ids);


    Package getDetail(Long id);

    IPage<Package> getPage(PackagePageQuery query);
}