package com.apexsystems.kafka;

import com.apexsystems.kafka.configuration.ProducerProperties;
import com.apexsystems.kafka.configuration.ConsumerLogProperties;
import com.apexsystems.kafka.kafka.KafkaWebRequestProducer;
import com.apexsystems.kafka.kafka.KafkaWebRequestConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitConfig {

    @Bean
    public KafkaWebRequestProducer kafkaProducer(ProducerProperties properties) {
        return new KafkaWebRequestProducer(properties);
    }

    @Bean
    public KafkaWebRequestConsumer kafkaConsumer(ConsumerLogProperties properties) {
        return new KafkaWebRequestConsumer(properties);
    }


}
