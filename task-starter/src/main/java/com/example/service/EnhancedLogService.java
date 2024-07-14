package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EnhancedLogService {
    private Logger logger;
    @Value("${log.level:trace}")
    private String level;

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
        switch (level.toLowerCase()) {
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
