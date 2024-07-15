package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("log")
public class LoggingProperties {
    /**
     * The logging level to be used by the application.
     */
    private String level = "info";

    public LoggingProperties() {
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
