package com.example.demo.mqdemo;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Recv {

    private final static String QUEUE_NAME = "hello";
    @Autowired
    private RabbitTemplate rabbitTemplate;



//    public void recv(){
//        Message receiveMsg = rabbitTemplate.receive(QUEUE_NAME);
//        assert receiveMsg != null;
//        byte[] body = receiveMsg.getBody();
//        System.out.println("接收到 "+ new String(body, StandardCharsets.UTF_8));
//    }

}