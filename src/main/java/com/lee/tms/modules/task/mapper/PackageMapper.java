package com.lee.tms.modules.task.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lee.tms.modules.task.entity.Package;
import org.apache.ibatis.annotations.Mapper;

/**
 * 分包信息表 Mapper 接口
 */
@Mapper
public interface PackageMapper extends BaseMapper<Package>
{}
