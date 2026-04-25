package com.example.demo.mqdemo.common.config;

import com.example.demo.mqdemo.MsgListener;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// 自动绑定。队列在声明时会自动绑定到默认交换机，且绑定键 (Binding Key) 就是队列名。
public class RabbitmqConfig {
    public static final String QUEUE_NAME = "listenQueue";

    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setChannelCacheSize(1);
        connectionFactory.setPort(5673);
        return connectionFactory;
    }

    @Bean
    public RabbitAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue myQueue() {
        return new Queue("myQueue");
    }

    @Bean
    public Queue queue(){
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(CachingConnectionFactory connectionFactory){
        SimpleMessageListenerContainer msgListenerContainer = new SimpleMessageListenerContainer();
        msgListenerContainer.setConnectionFactory(connectionFactory);
        msgListenerContainer.setQueues(queue());
        //设置消息监听器
        msgListenerContainer.setMessageListener(new MsgListener());
        msgListenerContainer.setConcurrentConsumers(1);
        msgListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return msgListenerContainer;
    }
}
