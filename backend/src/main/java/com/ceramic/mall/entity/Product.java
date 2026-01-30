package com.ceramic.mall.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 商品实体类
 * @author CeramicMall
 */
@Data
@TableName("products")
public class Product {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("description")
    private String description;

    @TableField("price")
    private Double price;

    @TableField("stock")
    private Integer stock;

    @TableField("category_id")
    private Long categoryId;

    @TableField("images")
    private String images;

    @TableField("sales")
    private Integer sales;

    @TableField("status")
    private Integer status;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableLogic
    @TableField("deleted")
    private Integer deleted;

    @TableField(exist = false)
    private List<String> imageList;
}