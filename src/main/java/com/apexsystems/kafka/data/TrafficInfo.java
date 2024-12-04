package com.apexsystems.kafka.data;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class TrafficInfo implements Serializable {
    Request request;
}
