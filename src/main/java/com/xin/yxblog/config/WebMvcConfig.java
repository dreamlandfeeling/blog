package com.xin.yxblog.config;

import com.xin.yxblog.filter.XssAndSqlFilter;
import com.xin.yxblog.interceptor.LoginInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor())
                .addPathPatterns("/")
                .addPathPatterns("/blog/**")
                .addPathPatterns("/comment/**")
                .addPathPatterns("/upload/**")
                .addPathPatterns("/user/**")
                .addPathPatterns("/generator/**")
                .excludePathPatterns("/blog/");

    }

    ///**
    // * 配置过滤器
    // * @return
    // */
    //@Bean
    //public FilterRegistrationBean someFilterRegistration() {
    //    FilterRegistrationBean registration = new FilterRegistrationBean();
    //    registration.setFilter(xssAndSqlFilter());
    //    registration.addUrlPatterns("/*");
    //    registration.addInitParameter("paramName", "paramValue");
    //    registration.setName("xssAndSqlFilter");
    //    return registration;
    //}
    //
    ///**
    // * 创建一个bean
    // * @return
    // */
    //@Bean(name = "xssAndSqlFilter")
    //public XssAndSqlFilter xssAndSqlFilter() {
    //    return new XssAndSqlFilter();
    //}
}
