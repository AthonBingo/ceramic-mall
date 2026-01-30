package com.ceramic.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ceramic.mall.entity.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 购物车Mapper接口
 * @author CeramicMall
 */
@Mapper
public interface CartMapper extends BaseMapper<Cart> {

    /**
     * 根据用户ID查询购物车列表，包含商品信息
     * @param userId 用户ID
     * @return 购物车列表
     */
    List<Cart> selectCartListByUserId(Long userId);

    /**
     * 根据用户ID和商品ID查询购物车记录
     * @param userId 用户ID
     * @param productId 商品ID
     * @return 购物车实体
     */
    Cart selectByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);

    /**
     * 清空用户购物车
     * @param userId 用户ID
     * @return 影响行数
     */
    int clearByUserId(Long userId);
}