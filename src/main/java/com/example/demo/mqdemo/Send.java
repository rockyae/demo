package com.example.demo.mqdemo;
import com.example.demo.mqdemo.common.config.RabbitmqConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;

import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
@Component
public class Send {
    private static final Logger log = LoggerFactory.getLogger(Send.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;



    public void send(String msg){
        String routingKey =  RabbitmqConfig.QUEUE_NAME;
        rabbitTemplate.send("", routingKey, new Message(msg.getBytes(StandardCharsets.UTF_8)
        ,new MessageProperties()));
        log.info("发送消息: {}",msg);
    }
}
