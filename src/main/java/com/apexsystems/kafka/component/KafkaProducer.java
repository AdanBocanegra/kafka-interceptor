package com.apexsystems.kafka.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.app.topic.name}")
    private String TOPIC_NAME;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String key,String message) {
        kafkaTemplate.send(TOPIC_NAME, key, message);
    }

}
