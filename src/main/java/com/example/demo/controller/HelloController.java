package com.example.demo.controller;


import cn.hutool.core.date.DateTime;
import com.example.demo.module.DateSet;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Date;

@RestController
public class HelloController {


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

    @GetMapping("/hello")
    public Student hello(@RequestHeader("MyHeader") String myHeader){
        return new Student("xiaoming",12,new Date());
    }

    @GetMapping("/date")
    public String printDate(){
        return new DateSet(new Date(),new java.sql.Date(new Date().getTime()),new DateTime()).toString();
    }

}
