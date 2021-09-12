package com.wxzy.aws.dynamodb.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxxxx
 **/
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Bean
    public FilterRegistrationBean<PluginHmacSignAuthFilter> pluginFilterRegistrationBean() {
        final FilterRegistrationBean<PluginHmacSignAuthFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(this.pluginHmacSignAuthFilter());
        registration.addUrlPatterns("/plugin/irrigation/*");
        registration.setName("pluginHmacSignAuthFilter");
        return registration;
    }

    @Bean
    public PluginHmacSignAuthFilter pluginHmacSignAuthFilter() {
        return new PluginHmacSignAuthFilter();
    }

}
