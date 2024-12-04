package com.apexsystems.kafka.component;

import com.apexsystems.kafka.kafka.KafkaWebRequestProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class KafkaRequestFilter implements WebFilter {
    private static final Logger log = LoggerFactory.getLogger(KafkaRequestFilter.class);
    private KafkaWebRequestProducer producer;
    @Value("${apex.test.producer.topic.name}")
    private String topicName;

    public KafkaRequestFilter(KafkaWebRequestProducer kafkaWebRequestProducer) {
        this.producer = kafkaWebRequestProducer;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        //log.info(exchange.getRequest().getPath().value());

        /*KafkaProducer producer = new KafkaProducer();*/
        try {
            producer.sendMessages(topicName, exchange.getRequest().getPath().value());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return chain.filter(exchange);
    }
}
