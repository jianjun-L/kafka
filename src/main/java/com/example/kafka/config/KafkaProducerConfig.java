package com.example.kafka.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: create by jianjunL
 * @version: v1.0
 * @description: com.dongao.project.config
 * @date:2019-09-09
 **/
@Configuration
@EnableKafka
public class KafkaProducerConfig {

    @Value("${bootstrap.servers}")
    private String servers;
    @Value("${kafka.producer.retries}")
    private int retries;
    @Value("${kafka.producer.batch.size}")
    private int batchSize;
    @Value("${kafka.producer.linger.ms}")
    private int linger;
    @Value("${kafka.producer.buffer.memory}")
    private int bufferMemory;
    @Value("${kafka.producer.acks}")
    private String acks;
    @Value("${kafka.producer.key.serializer}")
    private String key;
    @Value("${kafka.producer.value.serializer}")
    private String value;



    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        props.put(ProducerConfig.RETRIES_CONFIG, retries);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
        props.put(ProducerConfig.LINGER_MS_CONFIG, linger);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, key);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, value);
        props.put(ProducerConfig.ACKS_CONFIG,acks);
        return props;
    }

    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<String, String>(producerFactory());
    }
}