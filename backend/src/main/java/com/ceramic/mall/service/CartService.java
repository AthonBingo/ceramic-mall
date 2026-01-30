package com.ceramic.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ceramic.mall.entity.Cart;

import java.util.List;

/**
 * 购物车服务接口
 * @author CeramicMall
 */
public interface CartService extends IService<Cart> {

    /**
     * 添加到购物车
     * @param userId 用户ID
     * @param productId 商品ID
     * @param quantity 数量
     * @return 购物车实体
     */
    Cart addToCart(Long userId, Long productId, Integer quantity);

    /**
     * 获取购物车列表
     * @param userId 用户ID
     * @return 购物车列表
     */
    List<Cart> getCartList(Long userId);

    /**
     * 更新购物车商品数量
     * @param cartId 购物车ID
     * @param quantity 数量
     * @return 是否成功
     */
    boolean updateQuantity(Long cartId, Integer quantity);

    /**
     * 从购物车中移除商品
     * @param cartId 购物车ID
     * @return 是否成功
     */
    boolean removeFromCart(Long cartId);

    /**
     * 清空购物车
     * @param userId 用户ID
     * @return 是否成功
     */
    boolean clearCart(Long userId);

    /**
     * 计算购物车总金额
     * @param userId 用户ID
     * @return 总金额
     */
    Double calculateTotalAmount(Long userId);
}
