package com.example.demo;

import com.example.demo.controller.HelloController;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.example.demo.*")
public class DemoApplication {

    public static void main(String[] args) {


        SpringApplication.run(DemoApplication.class, args);

    }

}
