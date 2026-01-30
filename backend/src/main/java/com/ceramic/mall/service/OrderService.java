package com.ceramic.mall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ceramic.mall.entity.Order;

import java.util.List;

/**
 * 订单服务接口
 * @author CeramicMall
 */
public interface OrderService extends IService<Order> {

    /**
     * 创建订单（从购物车）
     * @param userId 用户ID
     * @param address 收货地址
     * @param phone 收货电话
     * @param receiver 收货人
     * @return 订单实体
     */
    Order createOrderFromCart(Long userId, String address, String phone, String receiver);

    /**
     * 获取订单详情
     * @param orderId 订单ID
     * @return 订单实体
     */
    Order getOrderDetail(Long orderId);

    /**
     * 分页查询订单列表
     * @param page 页码
     * @param size 每页数量
     * @param userId 用户ID
     * @param status 订单状态
     * @return 分页结果
     */
    Page<Order> getOrderList(int page, int size, Long userId, Integer status);

    /**
     * 取消订单
     * @param orderId 订单ID
     * @param userId 用户ID
     * @return 是否成功
     */
    boolean cancelOrder(Long orderId, Long userId);

    /**
     * 支付订单
     * @param orderId 订单ID
     * @param userId 用户ID
     * @return 是否成功
     */
    boolean payOrder(Long orderId, Long userId);

    /**
     * 发货
     * @param orderId 订单ID
     * @return 是否成功
     */
    boolean shipOrder(Long orderId);

    /**
     * 确认收货
     * @param orderId 订单ID
     * @param userId 用户ID
     * @return 是否成功
     */
    boolean confirmReceipt(Long orderId, Long userId);
}