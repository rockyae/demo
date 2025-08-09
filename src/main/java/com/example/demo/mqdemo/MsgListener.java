package com.example.demo.mqdemo;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
@Configuration
public class MsgListener implements ChannelAwareMessageListener {

    private static final Logger log = LoggerFactory.getLogger(MsgListener.class);

    @Override
    public void onMessage(Message message, Channel channel) throws IOException {
        try{
            byte[] body = message.getBody();
            String msg = new String(body, StandardCharsets.UTF_8);
            log.info("接收到消息: {}",msg);
//            long deliveryTag = message.getMessageProperties().getDeliveryTag();
//            channel.basicAck(deliveryTag, false);
//            log.info("已确认消息: {},tag为msg{}", msg,deliveryTag);

            processMessage(msg);
        }catch (Exception e){
            long deliveryTag = message.getMessageProperties().getDeliveryTag();
            //拒绝消息
            channel.basicReject(deliveryTag, false);
            log.info("处理消息出现异常,{},tag为msg{}", e.getMessage(),deliveryTag);
        }

    }
    private void processMessage(String msg) {
        // 示例：简单打印消息，可根据实际需求进行扩展
       // log.info("开始处理消息: {}", msg);
        // 这里可以添加具体的业务逻辑，如数据入库、调用其他服务等
        //假定msg为数字
        int num = Integer.parseInt(msg);
        if(num % 2 == 0){
            throw new RuntimeException("出现偶数消息");
        }
    }
}
