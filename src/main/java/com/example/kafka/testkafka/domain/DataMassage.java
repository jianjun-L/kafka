package com.example.kafka.testkafka.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * @author: create by jianjunL
 * @version: v1.0
 * @description: com.dongao.project.utils
 * @date:2019-09-09
 **/
@Data
public class DataMassage extends StringSerializer {
    private Integer type;
    private Long id;
}
