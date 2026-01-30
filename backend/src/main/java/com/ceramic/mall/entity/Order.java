package com.ceramic.mall.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 订单实体类
 * @author CeramicMall
 */
@Data
@TableName("orders")
public class Order {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("order_no")
    private String orderNo;

    @TableField("user_id")
    private Long userId;

    @TableField("total_amount")
    private Double totalAmount;

    @TableField("status")
    private Integer status;

    @TableField("address")
    private String address;

    @TableField("phone")
    private String phone;

    @TableField("receiver")
    private String receiver;

    @TableField("pay_time")
    private LocalDateTime payTime;

    @TableField("ship_time")
    private LocalDateTime shipTime;

    @TableField("complete_time")
    private LocalDateTime completeTime;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableLogic
    @TableField("deleted")
    private Integer deleted;

    @TableField(exist = false)
    private java.util.List<OrderItem> orderItems;
}