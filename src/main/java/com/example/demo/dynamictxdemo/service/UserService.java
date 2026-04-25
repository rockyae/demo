package com.example.demo.dynamictxdemo.service;

import com.example.demo.dynamictxdemo.context.DataSourceContextHolder;
import com.example.demo.dynamictxdemo.mapper.Db1UserMapper;
import com.example.demo.dynamictxdemo.mapper.Db2UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private Db1UserMapper db1UserMapper;
    @Autowired
    private Db2UserMapper db2UserMapper;

    /** 无事务：正常 */
    public void noTx() {
        DataSourceContextHolder.set("db1");
        db1UserMapper.insert("db1_no_tx");

        DataSourceContextHolder.set("db2");
        db2UserMapper.insert("db2_no_tx");
    }

    /** 单事务：切换失效 */
    @Transactional
    public void txFail() {
        int i = 1/0;
        DataSourceContextHolder.set("db1");
        db1UserMapper.insert("db1_tx");

        DataSourceContextHolder.set("db2");
        db2UserMapper.insert("db2_tx");

    }

//    /** 新事务：切换成功 */
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    public void txSuccess() {
//        DataSourceContextHolder.set("db1");
//        db1UserMapper.insert("db1_new_tx");
//    }
//
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    public void txSuccess2() {
//        DataSourceContextHolder.set("db2");
//        db2UserMapper.insert("db2_new_tx");
//    }
}