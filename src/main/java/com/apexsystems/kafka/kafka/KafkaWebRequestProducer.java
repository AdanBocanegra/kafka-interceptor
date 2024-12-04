package com.apexsystems.kafka.kafka;

import com.apexsystems.kafka.configuration.ProducerProperties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.*;

public class KafkaWebRequestProducer {

    private static final Logger log = LoggerFactory.getLogger(KafkaWebRequestProducer.class);
    private final KafkaProducer<String, String> sender;

    public KafkaWebRequestProducer(ProducerProperties props) {
        sender = new KafkaProducer<>(props);
    }

    public void sendMessages(String topic, String message) throws InterruptedException {
      sender.send(new ProducerRecord<String, String>(topic, Date.from(Instant.now()).toString(), message));
    }
}
