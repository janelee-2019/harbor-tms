package com.lee.tms.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.tms.modules.system.entity.Captcha;
import com.lee.tms.modules.system.mapper.CaptchaMapper;
import com.lee.tms.modules.system.service.CaptchaService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 */
@Service
public class CaptchaServiceImpl extends ServiceImpl<CaptchaMapper, Captcha> implements CaptchaService
{

}
