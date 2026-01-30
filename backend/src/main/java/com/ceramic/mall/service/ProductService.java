package com.ceramic.mall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ceramic.mall.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 商品服务接口
 * @author CeramicMall
 */
public interface ProductService extends IService<Product> {

    /**
     * 分页查询商品列表
     * @param page 页码
     * @param size 每页数量
     * @param categoryId 分类ID
     * @param keyword 搜索关键词
     * @return 分页结果
     */
    Page<Product> getProductList(int page, int size, Long categoryId, String keyword);

    /**
     * 获取商品详情
     * @param id 商品ID
     * @return 商品实体
     */
    Product getProductDetail(Long id);

    /**
     * 创建商品
     * @param product 商品信息
     * @return 商品实体
     */
    Product createProduct(Product product);

    /**
     * 更新商品
     * @param id 商品ID
     * @param product 商品信息
     * @return 是否成功
     */
    boolean updateProduct(Long id, Product product);

    /**
     * 删除商品
     * @param id 商品ID
     * @return 是否成功
     */
    boolean deleteProduct(Long id);

    /**
     * 上传商品图片
     * @param file 图片文件
     * @return 图片URL
     */
    String uploadProductImage(MultipartFile file);

    /**
     * 查询热销商品
     * @param limit 数量
     * @return 商品列表
     */
    List<Product> getHotProducts(int limit);

    /**
     * 减少库存
     * @param productId 商品ID
     * @param quantity 数量
     * @return 是否成功
     */
    boolean decreaseStock(Long productId, Integer quantity);
}