package com.apexsystems.kafka;

import com.apexsystems.kafka.configuration.ProducerProperties;
import com.apexsystems.kafka.kafka.KafkaWebRequestProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class InitConfig {

    @Bean
    public KafkaWebRequestProducer kafkaProducer(ProducerProperties properties) {
        return new KafkaWebRequestProducer(properties);
    }


}
