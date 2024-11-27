package com.apexsystems.kafka.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
@ConfigurationProperties(prefix="spring.kafka")
public class ProducerProperties extends Properties {

    private String bootStrapServers;
    private String clientId;
    private String keySerializer;
    private String valueSerializer;
}
