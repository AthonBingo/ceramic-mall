package com.ceramic.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ceramic.mall.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单Mapper接口
 * @author CeramicMall
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 根据订单号查询订单
     * @param orderNo 订单号
     * @return 订单实体
     */
    Order selectByOrderNo(String orderNo);

    /**
     * 分页查询用户订单列表，包含订单详情
     * @param page 分页对象
     * @param userId 用户ID
     * @param status 订单状态
     * @return 分页结果
     */
    IPage<Order> selectOrderPage(Page<Order> page,
                                 @Param("userId") Long userId,
                                 @Param("status") Integer status);
}