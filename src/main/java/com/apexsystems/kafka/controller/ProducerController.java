package com.apexsystems.kafka.controller;

import com.apexsystems.kafka.component.KafkaProducer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class ProducerController {
    private final KafkaProducer kafkaProducer;

    public ProducerController(KafkaProducer kafkaProducer){
        this.kafkaProducer = kafkaProducer;
    }

    @Scheduled(fixedDelay = 2000)
    public void sendMessage() {
        kafkaProducer.sendMessage(null, "hello world");
    }

}
