package com.lee.tms.config;

import com.lee.tms.config.props.AuthProps;
import com.lee.tms.infrastructure.auth.TokenFilter;
import jakarta.servlet.Filter;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.HashMap;
import java.util.LinkedHashMap;

@Configuration
@EnableConfigurationProperties (AuthProps.class)
public class ShiroConfig
{
    /**
     * 管理 Shiro 各个组件的生命周期
     */
    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor()
    {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 配置 WebSecurityManager
     */
    @Bean
    public SecurityManager securityManager(Realm realm)
    {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager)
    {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);

        HashMap<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("token", new TokenFilter());

        // 在此处设置哪些路由走 Token 验证
        HashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/sys/auth/captcha.jpg", "anon");
        filterChainDefinitionMap.put("/sys/auth/login", "anon");
        filterChainDefinitionMap.put("/statistic/allianceReport/info", "anon");
        filterChainDefinitionMap.put("/statistic/allianceReport/list", "anon");
        filterChainDefinitionMap.put("/statistic/allianceReport/list2", "anon");
        filterChainDefinitionMap.put("/statistic/allianceReport/page", "anon");
        filterChainDefinitionMap.put("/statistic/allianceReport/total", "anon");
        filterChainDefinitionMap.put("/statistic/allianceReport/add", "anon");
        filterChainDefinitionMap.put("/statistic/allianceReport/update", "anon");
        filterChainDefinitionMap.put("/statistic/allianceReport/delete", "anon");
        filterChainDefinitionMap.put("/statistic/perfReport/export/excel", "anon");
        filterChainDefinitionMap.put("/statistic/perfReport/page", "anon");

        filterChainDefinitionMap.put("/**", "token");

        bean.setFilters(filterMap);
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return bean;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager)
    {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    @DependsOn ("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator()
    {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }
}