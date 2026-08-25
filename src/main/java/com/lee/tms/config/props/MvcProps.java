package com.lee.tms.config.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties (prefix = "tms.mvc")
@Data
public class MvcProps
{
    private String[] allowedOrigins;
}
