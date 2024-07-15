package com.example.config;

import com.example.interceptor.RequestInterceptor;
import com.example.service.EnhancedLogService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class LoggingRequestAutoConfigurationIT {
    @Test
    void shouldAutoConfigurationBeAppliedToApplication() {
        new WebApplicationContextRunner()
                .withConfiguration(AutoConfigurations.of(LoggingRequestAutoConfiguration.class))
                .withPropertyValues("log.level")
                .run(context -> {
                    assertThat(context).hasNotFailed()
                            .hasSingleBean(LoggingProperties.class)
                            .hasSingleBean(RequestInterceptor.class)
                            .getBean(EnhancedLogService.class).hasNoNullFieldsOrProperties();
                });
    }
}