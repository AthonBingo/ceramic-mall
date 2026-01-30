package com.ceramic.mall.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ceramic.mall.entity.Cart;
import com.ceramic.mall.entity.Order;
import com.ceramic.mall.entity.OrderItem;
import com.ceramic.mall.entity.Product;
import com.ceramic.mall.mapper.CartMapper;
import com.ceramic.mall.mapper.OrderItemMapper;
import com.ceramic.mall.mapper.OrderMapper;
import com.ceramic.mall.mapper.ProductMapper;
import com.ceramic.mall.service.CartService;
import com.ceramic.mall.service.OrderService;
import com.ceramic.mall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.UUID;

/**
 * 订单服务实现类
 * @author CeramicMall
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final CartMapper cartMapper;
    private final ProductMapper productMapper;
    private final CartService cartService;
    private final ProductService productService;

    @Override
    @Transactional
    public Order createOrderFromCart(Long userId, String address, String phone, String receiver) {
        // 查询购物车
        List<Cart> cartList = cartService.getCartList(userId);
        if (cartList.isEmpty()) {
            throw new RuntimeException("购物车为空");
        }

        // 计算总金额
        DoubleSummaryStatistics stats = cartList.stream()
                .mapToDouble(cart -> {
                    if (cart.getProduct() != null) {
                        return cart.getProduct().getPrice() * cart.getQuantity();
                    }
                    return 0.0;
                })
                .summaryStatistics();
        double totalAmount = stats.getSum();

        // 检查库存并锁库存
        for (Cart cart : cartList) {
            Product product = cart.getProduct();
            if (product == null) {
                throw new RuntimeException("商品不存在");
            }
            if (product.getStock() < cart.getQuantity()) {
                throw new RuntimeException("商品 " + product.getName() + " 库存不足");
            }
        }

        // 创建订单
        Order order = new Order();
        order.setOrderNo(generateOrderNo());
        order.setUserId(userId);
        order.setTotalAmount(totalAmount);
        order.setStatus(0); // 待支付
        order.setAddress(address);
        order.setPhone(phone);
        order.setReceiver(receiver);
        order.setCreateTime(LocalDateTime.now());
        save(order);

        // 创建订单详情
        List<OrderItem> orderItems = cartList.stream()
                .map(cart -> {
                    OrderItem item = new OrderItem();
                    item.setOrderId(order.getId());
                    item.setProductId(cart.getProductId());
                    item.setProductName(cart.getProduct().getName());
                    item.setProductPrice(cart.getProduct().getPrice());
                    item.setQuantity(cart.getQuantity());
                    item.setTotalPrice(cart.getProduct().getPrice() * cart.getQuantity());
                    item.setCreateTime(LocalDateTime.now());
                    return item;
                })
                .toList();

        orderItemMapper.batchInsert(orderItems);

        // 减少库存
        for (Cart cart : cartList) {
            productMapper.decreaseStock(cart.getProductId(), cart.getQuantity());
            productMapper.increaseSales(cart.getProductId(), cart.getQuantity());
        }

        // 清空购物车
        cartMapper.clearByUserId(userId);

        return order;
    }

    @Override
    public Order getOrderDetail(Long orderId) {
        Order order = getById(orderId);
        if (order == null) {
            throw new RuntimeException("订单不存在");
        }

        // 获取订单详情
        List<OrderItem> orderItems = orderItemMapper.selectByOrderId(orderId);
        order.setOrderItems(orderItems);

        return order;
    }

    @Override
    public Page<Order> getOrderList(int page, int size, Long userId, Integer status) {
        Page<Order> pageParam = new Page<>(page, size);
        return orderMapper.selectOrderPage(pageParam, userId, status);
    }

    @Override
    public boolean cancelOrder(Long orderId, Long userId) {
        Order order = getById(orderId);
        if (order == null) {
            throw new RuntimeException("订单不存在");
        }

        if (!order.getUserId().equals(userId)) {
            throw new RuntimeException("无权操作此订单");
        }

        if (order.getStatus() != 0) {
            throw new RuntimeException("当前订单状态无法取消");
        }

        order.setStatus(5); // 已取消
        updateById(order);

        // 恢复库存
        List<OrderItem> orderItems = orderItemMapper.selectByOrderId(orderId);
        for (OrderItem item : orderItems) {
            productMapper.decreaseStock(item.getProductId(), -item.getQuantity()); // 负数为增加
            productMapper.increaseSales(item.getProductId(), -item.getQuantity()); // 负数为减少
        }

        return true;
    }

    @Override
    public boolean payOrder(Long orderId, Long userId) {
        Order order = getById(orderId);
        if (order == null) {
            throw new RuntimeException("订单不存在");
        }

        if (!order.getUserId().equals(userId)) {
            throw new RuntimeException("无权操作此订单");
        }

        if (order.getStatus() != 0) {
            throw new RuntimeException("订单状态不正确");
        }

        order.setStatus(1); // 已支付
        order.setPayTime(LocalDateTime.now());
        return updateById(order);
    }

    @Override
    public boolean shipOrder(Long orderId) {
        Order order = getById(orderId);
        if (order == null) {
            throw new RuntimeException("订单不存在");
        }

        if (order.getStatus() != 1) {
            throw new RuntimeException("订单状态不正确");
        }

        order.setStatus(2); // 已发货
        order.setShipTime(LocalDateTime.now());
        return updateById(order);
    }

    @Override
    public boolean confirmReceipt(Long orderId, Long userId) {
        Order order = getById(orderId);
        if (order == null) {
            throw new RuntimeException("订单不存在");
        }

        if (!order.getUserId().equals(userId)) {
            throw new RuntimeException("无权操作此订单");
        }

        if (order.getStatus() != 2) {
            throw new RuntimeException("订单状态不正确");
        }

        order.setStatus(3); // 已完成
        order.setCompleteTime(LocalDateTime.now());
        return updateById(order);
    }

    /**
     * 生成订单号
     * @return 订单号
     */
    private String generateOrderNo() {
        return "CM" + System.currentTimeMillis() + UUID.randomUUID().toString().substring(0, 8);
    }
}