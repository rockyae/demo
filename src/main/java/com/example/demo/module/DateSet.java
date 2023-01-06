package com.example.demo.module;

import cn.hutool.core.date.DateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DateSet {

    Date date;
    java.sql.Date dateSql;
    DateTime dateTime;

    public String toString(){
        return '{'+date.toString()+','+dateSql.toString()+','+dateTime.toString()+'}';
    }

}
