package com.example.demo.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode
public class DemoData {
    private Integer id;

    @ExcelProperty("name")
    private String stuName;

    @ExcelProperty("date")
    private Date date;

    @ExcelProperty("age")
    private int age;
}
