package com.apexsystems.kafka.kafka;

import com.apexsystems.kafka.configuration.ProducerProperties;
import com.apexsystems.kafka.data.TrafficInfo;
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
    private final KafkaProducer<String, TrafficInfo> sender;


    public KafkaWebRequestProducer(){
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "sample-producer");
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);


        sender = new KafkaProducer<>(properties);
    }

    public KafkaWebRequestProducer(ProducerProperties props) {
        sender = new KafkaProducer<>(props);
    }

    public void sendMessages(String topic, TrafficInfo message) throws InterruptedException {
      sender.send(new ProducerRecord<String, TrafficInfo>(topic, Date.from(Instant.now()).toString(), message));
    }
}
