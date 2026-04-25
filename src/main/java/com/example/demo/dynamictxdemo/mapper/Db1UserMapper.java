package com.example.demo.dynamictxdemo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


public interface Db1UserMapper {
    @Insert("INSERT INTO user(name) VALUES(#{name})")
    void insert(String name);
}
