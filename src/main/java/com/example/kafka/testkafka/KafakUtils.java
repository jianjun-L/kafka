package com.example.kafka.testkafka;

import com.example.kafka.testkafka.domain.DataMassage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: create by jianjunL
 * @version: v1.0
 * @description: com.dongao.project.utils
 * @date:2019-09-09
 **/
@Slf4j
@RestController
public class KafakUtils {

    @Resource
    private KafkaTemplate kafkaTemplate;
    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public void sendKafka(HttpServletRequest request) {
       /* String msg = request.getParameter("msg");*/
        DataMassage data = new DataMassage();
        data.setId(1030L);
        data.setType(1);
        String topicName = "my-replicated-topic";
        try {
            log.info("kafka的消息={}", data);
            ListenableFuture<SendResult<String,String>> future =  kafkaTemplate.send(topicName, data);
            future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                @Override
                public void onFailure(Throwable throwable) {

                }

                @Override
                public void onSuccess(SendResult<String, String> message) {
                    log.info("发送的消息是：{},with offset:{}",message,message.getRecordMetadata().offset());
                }
            });
            log.info("发送kafka成功=====>topicName:{},=========>message:{}",topicName,data);
        } catch (Exception e) {
            log.error("发送kafka失败=====>topicName:{},=========>message:{},======>e:{}",topicName,data,e);
        }
    }

   /* public static void main(String[] args) {
        DataMassage dataMassage = new DataMassage();
        dataMassage.setId(1L);
        dataMassage.setType(1);
        KafakUtils kafakUtils = new KafakUtils();
        kafakUtils.sendKafka(dataMassage);
        log.info("=============data:{}",dataMassage);
        log.info("============data:{}",dataMassage.toString());

    }*/
}
