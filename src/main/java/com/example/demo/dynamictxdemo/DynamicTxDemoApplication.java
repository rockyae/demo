package com.example.demo.dynamictxdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@MapperScan("com.example.demo.dynamictxdemo.mapper")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DynamicTxDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DynamicTxDemoApplication.class, args);
    }
}
