package com.example.demo.dynamictxdemo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


public interface Db2UserMapper {
    @Insert("INSERT INTO user(name) VALUES(#{name})")
    void insert(String name);
}