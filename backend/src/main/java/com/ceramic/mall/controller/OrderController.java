package com.ceramic.mall.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ceramic.mall.entity.Order;
import com.ceramic.mall.service.OrderService;
import com.ceramic.mall.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 订单控制器
 * @author CeramicMall
 */
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Tag(name = "订单管理", description = "订单相关API")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    @Operation(summary = "从购物车创建订单")
    public Result<Order> createOrderFromCart(@RequestParam Long userId,
                                             @RequestParam String address,
                                             @RequestParam String phone,
                                             @RequestParam String receiver) {
        Order order = orderService.createOrderFromCart(userId, address, phone, receiver);
        return Result.success(order);
    }

    @GetMapping("/{orderId}")
    @Operation(summary = "获取订单详情")
    public Result<Order> getOrderDetail(@PathVariable Long orderId) {
        Order order = orderService.getOrderDetail(orderId);
        return Result.success(order);
    }

    @GetMapping("/list")
    @Operation(summary = "分页查询订单列表")
    public Result<Page<Order>> getOrderList(@RequestParam(defaultValue = "1") int page,
                                            @RequestParam(defaultValue = "10") int size,
                                            @RequestParam Long userId,
                                            @RequestParam(required = false) Integer status) {
        Page<Order> orderPage = orderService.getOrderList(page, size, userId, status);
        return Result.success(orderPage);
    }

    @PostMapping("/{orderId}/cancel")
    @Operation(summary = "取消订单")
    public Result<Boolean> cancelOrder(@PathVariable Long orderId,
                                       @RequestParam Long userId) {
        boolean canceled = orderService.cancelOrder(orderId, userId);
        return Result.success(canceled);
    }

    @PostMapping("/{orderId}/pay")
    @Operation(summary = "支付订单")
    public Result<Boolean> payOrder(@PathVariable Long orderId,
                                    @RequestParam Long userId) {
        boolean paid = orderService.payOrder(orderId, userId);
        return Result.success(paid);
    }

    @PostMapping("/{orderId}/ship")
    @Operation(summary = "发货")
    public Result<Boolean> shipOrder(@PathVariable Long orderId) {
        boolean shipped = orderService.shipOrder(orderId);
        return Result.success(shipped);
    }

    @PostMapping("/{orderId}/confirm-receipt")
    @Operation(summary = "确认收货")
    public Result<Boolean> confirmReceipt(@PathVariable Long orderId,
                                        @RequestParam Long userId) {
        boolean confirmed = orderService.confirmReceipt(orderId, userId);
        return Result.success(confirmed);
    }
}