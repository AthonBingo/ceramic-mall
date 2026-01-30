package com.ceramic.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ceramic.mall.entity.Cart;
import com.ceramic.mall.entity.Product;
import com.ceramic.mall.mapper.CartMapper;
import com.ceramic.mall.mapper.ProductMapper;
import com.ceramic.mall.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;

/**
 * 购物车服务实现类
 * @author CeramicMall
 */
@Service
@RequiredArgsConstructor
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    private final CartMapper cartMapper;
    private final ProductMapper productMapper;

    @Override
    public Cart addToCart(Long userId, Long productId, Integer quantity) {
        // 查询商品
        Product product = productMapper.selectById(productId);
        if (product == null) {
            throw new RuntimeException("商品不存在");
        }

        // 检查库存
        if (product.getStock() < quantity) {
            throw new RuntimeException("库存不足");
        }

        // 查询购物车中是否已存在该商品
        Cart existingCart = cartMapper.selectByUserIdAndProductId(userId, productId);
        if (existingCart != null) {
            // 更新数量
            existingCart.setQuantity(existingCart.getQuantity() + quantity);
            updateById(existingCart);
            return existingCart;
        }

        // 创建新的购物车记录
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setProductId(productId);
        cart.setQuantity(quantity);
        cart.setCreateTime(LocalDateTime.now());
        save(cart);

        return cart;
    }

    @Override
    public List<Cart> getCartList(Long userId) {
        return cartMapper.selectCartListByUserId(userId);
    }

    @Override
    public boolean updateQuantity(Long cartId, Integer quantity) {
        Cart cart = getById(cartId);
        if (cart == null) {
            throw new RuntimeException("购物车记录不存在");
        }

        cart.setQuantity(quantity);
        return updateById(cart);
    }

    @Override
    public boolean removeFromCart(Long cartId) {
        return removeById(cartId);
    }

    @Override
    public boolean clearCart(Long userId) {
        return cartMapper.clearByUserId(userId) > 0;
    }

    @Override
    public Double calculateTotalAmount(Long userId) {
        List<Cart> cartList = getCartList(userId);
        if (cartList.isEmpty()) {
            return 0.0;
        }

        DoubleSummaryStatistics stats = cartList.stream()
                .mapToDouble(cart -> {
                    if (cart.getProduct() != null) {
                        return cart.getProduct().getPrice() * cart.getQuantity();
                    }
                    return 0.0;
                })
                .summaryStatistics();

        return stats.getSum();
    }
}
