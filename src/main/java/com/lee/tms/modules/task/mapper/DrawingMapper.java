package com.lee.tms.modules.task.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lee.tms.modules.task.entity.Drawing;
import org.apache.ibatis.annotations.Mapper;

/**
 * 图纸信息表 Mapper 接口
 */
@Mapper
public interface DrawingMapper extends BaseMapper<Drawing>
{}
