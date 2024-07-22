package com.example.producer;

import org.springframework.kafka.core.KafkaTemplate;

public class Sender {
    private final KafkaTemplate<Integer, String> kafkaTemplate;
    private final String topicName;

    public Sender(KafkaTemplate<Integer, String> kafkaTemplate, String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    public void send(int key, String message) {
        this.kafkaTemplate.send(this.topicName, key, message);
    }
}
