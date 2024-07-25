package com.example.config;

import com.example.consumer.Listener;
import com.example.dto.Status;
import com.example.service.StatusService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.ParseStringDeserializer;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class ConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ConsumerFactory<Integer, Status> consumerFactory() {
        ConsumerFactory<Integer, Status> consumerFactory = new DefaultKafkaConsumerFactory<>(
                consumerConfigs(),
                new IntegerDeserializer(),
                new ParseStringDeserializer<>(Status::parse));
        consumerFactory.addListener(new ConsumerFactory.Listener<Integer, Status>() {
            @Override
            public void consumerAdded(String id, Consumer<Integer, Status> consumer) {
                log.info("Consumer: " + id);
                ConsumerFactory.Listener.super.consumerAdded(id, consumer);
            }

            @Override
            public void consumerRemoved(String id, Consumer<Integer, Status> consumer) {
                ConsumerFactory.Listener.super.consumerRemoved(id, consumer);
            }
        });
        return consumerFactory;
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Integer, Status>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, Status> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }

    @Bean
    public Listener listener(StatusService statusService) {
        return new Listener(statusService);
    }

    private Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers);
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }
}
