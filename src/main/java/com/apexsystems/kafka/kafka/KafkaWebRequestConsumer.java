package com.apexsystems.kafka.kafka;

import com.apexsystems.kafka.configuration.ConsumerLogProperties;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;

public class KafkaWebRequestConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaWebRequestConsumer.class);
    private final KafkaConsumer<String, String> receiver;

    public KafkaWebRequestConsumer(ConsumerLogProperties props) {
        receiver = new KafkaConsumer<String, String>(props);
    }

    public void consume(String topic) {
        receiver.subscribe(Collections.singleton(topic));
    }

    public ConsumerRecords<String, String> getRecords(){
        return receiver.poll(Duration.ofMillis(100));
    }
}
