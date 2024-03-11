package com.example.demo.controller;


import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSON;
import com.example.demo.module.KeyConfig;
import com.example.demo.module.DateSet;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Date;

@RestController
public class HelloController implements BeanNameAware, BeanFactoryAware , BeanPostProcessor {
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }

    @Nullable
    private String beanName;

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Data
    @AllArgsConstructor
    class Student implements Serializable {
        @JsonProperty("fme")
        private String fName;

        @JsonProperty("fAge")       //@JsonProperty可以fage --> fAge
        private Integer fAge;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private Date birthDay;
    }

    @Autowired
    private KeyConfig keyConfig;

    @GetMapping("/hello")
    public Student hello(@RequestHeader("MyHeader") String myHeader){
        return new Student("xiaoming",12,new Date());
    }

    @GetMapping("/date")
    public String printDate(){
        return new DateSet(new Date(),new java.sql.Date(new Date().getTime()),new DateTime()).toString();
    }

    @GetMapping("/printBeanName")
    public String printBeanName(){
        return this.beanName;
    }

    /**
     * 注入数组测试
     */
    @GetMapping("/inject")
    public String inject(){
        System.out.println(keyConfig.toString());
        return JSON.toJSONString(keyConfig);
    }
}
