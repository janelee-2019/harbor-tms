package com.lee.tms.config.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties (prefix = "tms.auth")
@Data
public class AuthProps
{
    private int captchaWidth = 180;

    private int captchaHeight = 60;

    private int captchaExpiresInMinutes = 5;

    private int tokenExpiresInDays = 7;

    private String defaultLoginPassword = "123456";
}
