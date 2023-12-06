package com.example.demo;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson.JSON;
import com.example.demo.easyexcel.DemoData;
import com.example.demo.easyexcel.DemoDataListener;
import com.example.demo.easyexcel.DemoMapper;
import com.example.demo.module.Point;
import com.example.demo.util.TestFileUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.InputStream;
import java.util.HashSet;


@SpringBootTest
@Slf4j
class DemoApplicationTests {

    @Autowired
    private DemoMapper demoMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisTemplate redisForString;
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

}
