package com.apexsystems.kafka.component;

import com.apexsystems.kafka.data.Request;
import com.apexsystems.kafka.data.TrafficInfo;
import com.apexsystems.kafka.kafka.KafkaWebRequestProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import java.util.Objects;

@Component
public class KafkaRequestFilter implements WebFilter {
    private static final Logger log = LoggerFactory.getLogger(KafkaRequestFilter.class);
    private KafkaWebRequestProducer producer;

    public KafkaRequestFilter(KafkaWebRequestProducer kafkaWebRequestProducer) {
        this.producer = kafkaWebRequestProducer;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        log.info(exchange.getRequest().getPath().value());
        Request request = Request.builder()
                .path(exchange.getRequest().getPath().value())
                .hostName(Objects.requireNonNull(exchange.getRequest().getLocalAddress()).getHostName())
                .queryParams(exchange.getRequest().getQueryParams().toSingleValueMap())
                .build();
        TrafficInfo trafficInfo = TrafficInfo.builder()
                .request(request)
                .build();

        try {
            producer.sendMessages("demo-topic", trafficInfo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return chain.filter(exchange);
    }
}
