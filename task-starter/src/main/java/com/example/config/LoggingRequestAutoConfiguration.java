package com.example.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ComponentScan("com.example")
@ConditionalOnProperty(value="log.level")
public class LoggingRequestAutoConfiguration implements WebMvcConfigurer {
    private final HandlerInterceptor handlerInterceptor;

    public LoggingRequestAutoConfiguration(
            @Qualifier("requestInterceptor") HandlerInterceptor handlerInterceptor) {
        this.handlerInterceptor = handlerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.handlerInterceptor);
    }
}
