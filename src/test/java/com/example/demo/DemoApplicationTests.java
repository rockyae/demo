package com.example.demo;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson.JSON;
import com.example.demo.controller.HelloController;
import com.example.demo.easyexcel.DemoData;
import com.example.demo.easyexcel.DemoDataListener;
import com.example.demo.easyexcel.DemoMapper;
import com.example.demo.module.Point;
import com.example.demo.mqdemo.Send;
import com.example.demo.tx.TestTranction;
import com.example.demo.util.TestFileUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.InputStream;
import java.util.HashSet;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;


@SpringBootTest
@Slf4j
class DemoApplicationTests {

    @Autowired
    private DemoMapper demoMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisTemplate redisForString;

    @Autowired
    private Send send;

    @Autowired
    private TestTranction testTranction;

    int a = 10;
    @Test
    public void simpleRead(){
        log.info("内部");
        String fileName = "/excel/demo.xlsx";
        InputStream inputStream = getClass().getResourceAsStream(fileName);
        EasyExcel.read(inputStream, DemoData.class,new DemoDataListener(demoMapper)).sheet().doRead();
    }

    @Test
    public void redisTest() throws JsonProcessingException {
        Point point = new Point();
        /**
         *  默认jdk序列化器的序列化
         *  key:\xAC\xED\x00\x05t\x00\x05point
         *  value:\xAC\xED\x00\x05sr\x00\x1Dcom.example.demo.module.Point\x00\x00\x00\x00\x00\x00\x00\x01\x02\x00\x02I\x00\x01xI\x00\x01yxp\x00\x00\x00\x00\x00\x00\x00\x00
         */


        redisTemplate.opsForValue().set("point",point);
        Point point1 = (Point) redisTemplate.opsForValue().get("point");
        System.out.println(point1);

    }

    @Test
    public void hashSetTest(){
        //

    }

    @Test
    public void iocTest() throws InterruptedException {
        long start = 0;
        Thread t = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                System.out.println(i);
               if(i == 50){
                   LockSupport.park();
               }
            }
        });
        try{
             start = System.currentTimeMillis();
            t.start();
        }finally {
            Thread.sleep(3000);
            LockSupport.unpark(t);
            long duration = System.currentTimeMillis()-start;
            System.out.println("finally,耗时"+duration/1000+"s");
        }
    }

    @Test
    public void lockTest() throws InterruptedException {
        ReentrantLock o = new ReentrantLock(true);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                o.lock();
                for(int i=0; i<100000; i++){
                    a--;
                    System.out.println(a);
                }
                System.out.println("t1结束");
                o.unlock();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                o.lock();
                for(int i=0; i<100000; i++){
                    a++;
                    System.out.println(a);
                }
                o.unlock();
                System.out.println("t2结束");
            }
        });
        t1.setName("t1");
        t2.setName("t2");
        t1.start();
        t2.start();
        Thread.sleep(50000);
        System.out.println(a);
    }


//    @Test
//    public void MqTest() throws InterruptedException {
////        send.send("你好");
//        for (int i = 0; i < 4; i++) {
//            send.send(String.valueOf(i));
//            Thread.sleep(1000);
//        }
//    }

    @Test
    public void txTest(){
        testTranction.testCreate();
    }


}
