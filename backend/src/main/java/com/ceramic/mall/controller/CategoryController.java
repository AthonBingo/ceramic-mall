package com.ceramic.mall.controller;

import com.ceramic.mall.entity.Category;
import com.ceramic.mall.service.CategoryService;
import com.ceramic.mall.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类控制器
 * @author CeramicMall
 */
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Tag(name = "分类管理", description = "商品分类相关API")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/top-level")
    @Operation(summary = "获取所有顶级分类")
    public Result<List<Category>> getTopLevelCategories() {
        List<Category> categories = categoryService.getTopLevelCategories();
        return Result.success(categories);
    }

    @GetMapping("/children/{parentId}")
    @Operation(summary = "获取子分类")
    public Result<List<Category>> getChildrenCategories(@PathVariable Long parentId) {
        List<Category> categories = categoryService.getChildrenCategories(parentId);
        return Result.success(categories);
    }

    @GetMapping
    @Operation(summary = "获取所有分类")
    public Result<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.list();
        return Result.success(categories);
    }

    @PostMapping
    @Operation(summary = "创建分类")
    public Result<Category> createCategory(@RequestBody Category category) {
        Category savedCategory = categoryService.createCategory(category);
        return Result.success(savedCategory);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新分类")
    public Result<Boolean> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        boolean updated = categoryService.updateCategory(id, category);
        return Result.success(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除分类")
    public Result<Boolean> deleteCategory(@PathVariable Long id) {
        boolean deleted = categoryService.deleteCategory(id);
        return Result.success(deleted);
    }
}