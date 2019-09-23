package com.example.kafka.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * @author: create by jianjunL
 * @version: v1.0
 * @description: com.dongao.project.utils
 * @date:2019-09-09
 **/
public class DataMessagaSerializer implements Serializer {
    private ObjectMapper objectMapper;

    @Override
    public void configure(Map map, boolean b) {
        objectMapper = new ObjectMapper();
    }

    @Override
    public byte[] serialize(String topic, Object data) {
        byte[] ret = null;
        try {
            ret = objectMapper.writeValueAsString(data).getBytes("utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
    @Override
    public void close() {

    }
}
