package com.example.starter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ComponentScan("com.example")
public class LogRequestAutoConfiguration implements WebMvcConfigurer {
    private final HandlerInterceptor handlerInterceptor;

    public LogRequestAutoConfiguration(
            @Qualifier("requestInterceptor") HandlerInterceptor handlerInterceptor) {
        this.handlerInterceptor = handlerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.handlerInterceptor);
    }
}
