package com.apexsystems.kafka.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
@ConfigurationProperties(prefix="spring.kafka.consumer")
public class ConsumerLogProperties extends Properties {

    private String bootStrapServers;
    private String groupId;
    private String keySerializer;
    private String valueSerializer;
}
