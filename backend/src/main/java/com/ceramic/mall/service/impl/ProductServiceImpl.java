package com.ceramic.mall.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ceramic.mall.entity.Product;
import com.ceramic.mall.mapper.ProductMapper;
import com.ceramic.mall.service.ProductService;
import com.ceramic.mall.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * 商品服务实现类
 * @author CeramicMall
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    private final ProductMapper productMapper;
    private final FileUtil fileUtil;

    @Override
    public Page<Product> getProductList(int page, int size, Long categoryId, String keyword) {
        Page<Product> pageParam = new Page<>(page, size);
        return productMapper.selectProductPage(pageParam, categoryId, keyword);
    }

    @Override
    public Product getProductDetail(Long id) {
        return getById(id);
    }

    @Override
    public Product createProduct(Product product) {
        product.setCreateTime(LocalDateTime.now());
        product.setSales(0);
        product.setStatus(1); // 上架

        // 处理图片列表
        if (product.getImageList() != null && !product.getImageList().isEmpty()) {
            product.setImages(String.join(",", product.getImageList()));
        }

        save(product);
        return product;
    }

    @Override
    public boolean updateProduct(Long id, Product product) {
        product.setId(id);

        // 处理图片列表
        if (product.getImageList() != null && !product.getImageList().isEmpty()) {
            product.setImages(String.join(",", product.getImageList()));
        }

        return updateById(product);
    }

    @Override
    public boolean deleteProduct(Long id) {
        return removeById(id);
    }

    @Override
    public String uploadProductImage(MultipartFile file) {
        return fileUtil.saveFile(file, "products");
    }

    @Override
    public List<Product> getHotProducts(int limit) {
        return productMapper.selectHotProducts(limit);
    }

    @Override
    public boolean decreaseStock(Long productId, Integer quantity) {
        return productMapper.decreaseStock(productId, quantity) > 0;
    }
}