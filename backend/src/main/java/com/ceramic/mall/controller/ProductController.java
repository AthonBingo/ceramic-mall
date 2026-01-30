package com.ceramic.mall.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ceramic.mall.entity.Product;
import com.ceramic.mall.service.ProductService;
import com.ceramic.mall.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 商品控制器
 * @author CeramicMall
 */
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "商品管理", description = "商品相关API")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @Operation(summary = "分页查询商品列表")
    public Result<Page<Product>> getProductList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String keyword) {
        Page<Product> productPage = productService.getProductList(page, size, categoryId, keyword);
        return Result.success(productPage);
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取商品详情")
    public Result<Product> getProductDetail(@PathVariable Long id) {
        Product product = productService.getProductDetail(id);
        return Result.success(product);
    }

    @PostMapping
    @Operation(summary = "创建商品")
    public Result<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.createProduct(product);
        return Result.success(savedProduct);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新商品")
    public Result<Boolean> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        boolean updated = productService.updateProduct(id, product);
        return Result.success(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除商品")
    public Result<Boolean> deleteProduct(@PathVariable Long id) {
        boolean deleted = productService.deleteProduct(id);
        return Result.success(deleted);
    }

    @GetMapping("/hot")
    @Operation(summary = "获取热销商品")
    public Result<List<Product>> getHotProducts(@RequestParam(defaultValue = "10") int limit) {
        List<Product> products = productService.getHotProducts(limit);
        return Result.success(products);
    }

    @PostMapping("/upload-image")
    @Operation(summary = "上传商品图片")
    public Result<String> uploadProductImage(@RequestParam("file") MultipartFile file) {
        String imageUrl = productService.uploadProductImage(file);
        return Result.success(imageUrl);
    }
}