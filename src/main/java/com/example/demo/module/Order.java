package com.example.demo.module;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@TableName("orders")
@Data
public class Order {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("customer_name")
    private String customerName;

    @TableField("order_date")
    private LocalDate orderDate;

    @TableField(exist = false)
    private List<OrderDetail> orderDetails;

}
