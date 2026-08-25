package com.lee.tms.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lee.tms.modules.system.entity.Token;

/**
 * <p>
 * Mapper 接口
 * </p>
 */
public interface TokenMapper extends BaseMapper<Token>
{
    Token selectTokenWithUser(String token);
}
