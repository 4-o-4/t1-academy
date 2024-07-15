package com.example.service;

import com.example.config.LoggingProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@EnableConfigurationProperties(LoggingProperties.class)
public class EnhancedLogService {
    private final String level;
    private Logger logger;

    public EnhancedLogService(LoggingProperties loggingProperties) {
        this.level = loggingProperties.getLevel().toLowerCase();
    }

    public EnhancedLogService setNameLogger(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
        return this;
    }

    /**
     * Logs a message at the configured logging level.
     *
     * @param message the message to log
     */
    public void log(String message) {
        switch (level) {
            case "error":
                logger.error(message);
                break;
            case "warn":
                logger.warn(message);
                break;
            case "info":
                logger.info(message);
                break;
            case "debug":
                logger.debug(message);
                break;
            default:
                logger.trace(message);
        }
    }
}
