package com.apexsystems.kafka.component;

import com.apexsystems.kafka.kafka.KafkaWebRequestConsumer;
import jakarta.annotation.PostConstruct;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class KafkaRequestConsumer {
    private static final Logger log = LoggerFactory.getLogger(KafkaRequestConsumer.class);
    private final KafkaWebRequestConsumer consumer;

    @Value("${apex.test.consumer.topic.name}")
    private String topicName;


    public KafkaRequestConsumer(KafkaWebRequestConsumer kafkaWebRequestConsumer) {
        this.consumer = kafkaWebRequestConsumer;
    }

    @PostConstruct
    public void init(){
        consumer.consume(topicName);
    }

    @Scheduled(fixedDelay = 100)
    public void runTask(){
        ConsumerRecords<String, String> records = consumer.getRecords();

        for (ConsumerRecord<String, String> record : records) {
            log.info("Key: " + record.key() + ", Value: " + record.value());
            log.info("Partition: " + record.partition() + ", Offset:" + record.offset());
        }
    }

}
