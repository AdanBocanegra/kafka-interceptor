package com.apexsystems.kafka.kafka;

import com.apexsystems.kafka.data.TrafficInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.core.serializer.support.SerializationFailedException;

public class TrafficInfoSerializer implements Serializer<TrafficInfo> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public byte[] serialize(String topic, TrafficInfo trafficInfo) {
        if(trafficInfo == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsBytes(trafficInfo);
        } catch (JsonProcessingException jpe) {
            throw new SerializationFailedException("Error when serializing object");
        }
    }
}
