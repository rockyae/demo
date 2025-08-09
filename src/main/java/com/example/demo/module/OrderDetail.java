package com.example.demo.module;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@TableName("order_details")
@Data
public class OrderDetail {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("order_id")
    private Integer orderId;

    @TableField("product_name")
    private String productName;

    private int quantity;

    private BigDecimal price;
}
