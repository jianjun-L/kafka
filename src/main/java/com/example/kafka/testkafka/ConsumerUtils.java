package com.example.kafka.testkafka;

import com.example.kafka.testkafka.domain.DataMassage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author: create by jianjunL
 * @version: v1.0
 * @description: com.example.kafka.testkafka
 * @date:2019-09-20
 **/
@Slf4j
@Component
public class ConsumerUtils {
    @KafkaListener(topics = { "my-replicated-topic" })
    public void listen(ConsumerRecord<?, ?> record) {
        DataMassage data = (DataMassage) record.value();
        log.info("接收到的消息是：id:{},type:{}",data.getId(),data.getType());
    }
}
