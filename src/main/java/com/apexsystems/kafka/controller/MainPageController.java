package com.apexsystems.kafka.controller;

import com.apexsystems.kafka.component.KafkaProducer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    private final KafkaProducer kafkaProducer;

    public MainPageController(KafkaProducer kafkaProducer){
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping("/")
    public String sendMessage(HttpServletRequest request) {
        kafkaProducer.sendMessage(request.getMethod(), request.getRequestURL().toString());
        return "index";
    }
}
