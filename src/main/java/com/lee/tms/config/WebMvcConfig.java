package com.lee.tms.config;

import com.lee.tms.config.props.MvcProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableConfigurationProperties (MvcProps.class)
@Configuration
public class WebMvcConfig implements WebMvcConfigurer
{
    @Autowired
    private MvcProps mvcProps;

    /**
     * 跨域配置
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry)
    {
        registry.addMapping("/**")
                //                .allowedOriginPatterns("*")
                .allowedOrigins(mvcProps.getAllowedOrigins())
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .maxAge(3600);
    }
}
