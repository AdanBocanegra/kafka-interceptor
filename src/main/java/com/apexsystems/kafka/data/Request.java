package com.apexsystems.kafka.data;

import lombok.Builder;
import lombok.Data;

import java.io.*;
import java.util.Map;

@Data
@Builder
public class Request implements Serializable {
    String path;
    String hostName;
    Map<String, String> queryParams;

    @Serial
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        //objectOutputStream.writeObject();
    }

    @Serial
    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException{
        objectInputStream.defaultReadObject();
    }
}
