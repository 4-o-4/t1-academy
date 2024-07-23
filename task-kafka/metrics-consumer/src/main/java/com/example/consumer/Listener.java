package com.example.consumer;

import com.example.dto.Status;
import com.example.service.StatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
public class Listener {
    private final StatusService statusService;

    public Listener(StatusService statusService) {
        this.statusService = statusService;
    }

    @KafkaListener(id = "metrics", topics = "${topic.name}", containerFactory = "kafkaListenerContainerFactory")
    public void listen(Status msg) {
        log.info("Listen: " + msg);
        this.statusService.save(msg);
    }
}
