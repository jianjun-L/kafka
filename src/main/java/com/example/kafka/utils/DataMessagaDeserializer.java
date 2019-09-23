package com.example.kafka.utils;

import com.example.kafka.testkafka.domain.DataMassage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.io.IOException;
import java.util.Map;

/**
 * @author: create by jianjunL
 * @version: v1.0
 * @description: com.dongao.project.utils
 * @date:2019-09-09
 **/
public class DataMessagaDeserializer implements Deserializer {
    private ObjectMapper objectMapper;

    @Override
    public void configure(Map map, boolean b) {
        objectMapper = new ObjectMapper();
    }


    @Override
    public Object deserialize(String topic, byte[] bytes) {
        Object obj = null;
        try {
            System.out.println("===================topic："+topic);
            System.out.println("===================bytes："+bytes.toString());
            obj = objectMapper.readValue(bytes, DataMassage.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void close() {

    }
}
