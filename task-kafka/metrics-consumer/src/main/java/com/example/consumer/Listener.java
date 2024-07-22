package com.example.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
public class Listener {
    @KafkaListener(id = "metrics", topics = "${topic.name}")
    public void listen(String message) {
        log.info("Listen: " + message);
    }
}
