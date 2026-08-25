package com.lee.tms.modules.task.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lee.tms.modules.task.entity.Batch;
import org.apache.ibatis.annotations.Mapper;

/**
 * 批次信息表 Mapper 接口
 */
@Mapper
public interface BatchMapper extends BaseMapper<Batch>
{}
