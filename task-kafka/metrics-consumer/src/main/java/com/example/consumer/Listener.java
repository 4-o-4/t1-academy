package com.example.consumer;

import com.example.dto.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
public class Listener {
    @KafkaListener(id = "metrics", topics = "${topic.name}", containerFactory = "kafkaListenerContainerFactory")
    public void listen(Status msg) {
        log.info("Listen: " + msg);
    }
}
