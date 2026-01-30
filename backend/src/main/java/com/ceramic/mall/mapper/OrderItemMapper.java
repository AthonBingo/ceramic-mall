package com.ceramic.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ceramic.mall.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单详情Mapper接口
 * @author CeramicMall
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {

    /**
     * 根据订单ID查询订单详情列表，包含商品信息
     * @param orderId 订单ID
     * @return 订单详情列表
     */
    List<OrderItem> selectByOrderId(Long orderId);

    /**
     * 批量插入订单详情
     * @param orderItems 订单详情列表
     * @return 影响行数
     */
    int batchInsert(@Param("list") List<OrderItem> orderItems);
}