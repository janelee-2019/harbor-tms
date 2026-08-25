package com.lee.tms.modules.task.service.impl;

import cn.hutool.core.bean.BeanUtil;import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.tms.infrastructure.auth.AuthContext;
import com.lee.tms.infrastructure.rest.RestCode;
import com.lee.tms.infrastructure.rest.RestException;
import com.lee.tms.modules.task.dto.body.PackageAddBody;
import com.lee.tms.modules.task.dto.body.PackageUpdateBody;
import com.lee.tms.modules.task.dto.query.PackageQuery;
import com.lee.tms.modules.task.dto.query.PackagePageQuery;
import com.lee.tms.modules.task.entity.Package;
import com.lee.tms.modules.task.mapper.PackageMapper;
import com.lee.tms.modules.task.service.PackageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PackageServiceImpl extends ServiceImpl<PackageMapper, Package> implements PackageService
{
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertPackage(PackageAddBody body)
    {
        Package entity = new Package();
        BeanUtil.copyProperties(body, entity);
        entity.setCreateBy(AuthContext.getCurrentUserId());
        save(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePackage(PackageUpdateBody body)
    {
        Package entity = getDetail(body.getId());
        BeanUtil.copyProperties(body, entity);
        entity.setUpdateBy(AuthContext.getCurrentUserId());
        updateById(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deletePackage(List<Long> ids)
    {
        removeBatchByIds(ids);
    }

    @Override
    public Package getDetail(Long id)
    {
        Package entity = getById(id);
        if (entity == null)
        {
            throw new RestException(RestCode.SYS_ERR, "分包信息表不存在 [ID = {}]", id);
        }
        return entity;
    }

    @Override
    public IPage<Package> getPage(PackagePageQuery query)
    {
        IPage<Package> page = new Page<>(query.getPageNo(), query.getPageSize());
        LambdaQueryWrapper<Package> wrapper = new LambdaQueryWrapper<>();

        if (StrUtil.isNotBlank(query.getKeyword()))
        {
            wrapper.and(w ->
                    w.like(Package::getPackageNo, query.getKeyword())
                    .or().like(Package::getPackageType, query.getKeyword())
                    .or().like(Package::getRemark, query.getKeyword())
                    .or().like(Package::getSn, query.getKeyword())
                    .or().like(Package::getIsIssued, query.getKeyword())
                    .or().like(Package::getIsDistributed, query.getKeyword())
                    );
        }
        return baseMapper.selectPage(page, wrapper);
    }
}