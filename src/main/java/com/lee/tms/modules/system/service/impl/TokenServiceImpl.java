package com.lee.tms.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.tms.modules.system.entity.Token;
import com.lee.tms.modules.system.mapper.TokenMapper;
import com.lee.tms.modules.system.service.TokenService;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl extends ServiceImpl<TokenMapper, Token> implements TokenService
{
    @Override
    public Token getTokenWithUser(String token)
    {
        return baseMapper.selectTokenWithUser(token);
    }
}
