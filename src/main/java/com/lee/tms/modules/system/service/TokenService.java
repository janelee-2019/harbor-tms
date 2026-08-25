package com.lee.tms.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.tms.modules.system.entity.Token;

/**
 * <p>
 * 服务类
 * </p>
 */
public interface TokenService extends IService<Token>
{

    Token getTokenWithUser(String token);
}
