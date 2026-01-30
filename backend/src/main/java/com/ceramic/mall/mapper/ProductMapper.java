package com.ceramic.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ceramic.mall.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品Mapper接口
 * @author CeramicMall
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    /**
     * 分页查询商品列表
     * @param page 分页对象
     * @param categoryId 分类ID
     * @param keyword 搜索关键词
     * @return 分页结果
     */
    IPage<Product> selectProductPage(Page<Product> page,
                                     @Param("categoryId") Long categoryId,
                                     @Param("keyword") String keyword);

    /**
     * 查询热销商品
     * @param limit 数量限制
     * @return 商品列表
     */
    List<Product> selectHotProducts(@Param("limit") Integer limit);

    /**
     * 增加商品销量
     * @param productId 商品ID
     * @param quantity 销售数量
     * @return 影响行数
     */
    int increaseSales(@Param("productId") Long productId, @Param("quantity") Integer quantity);

    /**
     * 减少商品库存
     * @param productId 商品ID
     * @param quantity 减少数量
     * @return 影响行数
     */
    int decreaseStock(@Param("productId") Long productId, @Param("quantity") Integer quantity);
}