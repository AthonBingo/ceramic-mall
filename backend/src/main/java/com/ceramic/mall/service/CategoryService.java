package com.ceramic.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ceramic.mall.entity.Category;

import java.util.List;

/**
 * 分类服务接口
 * @author CeramicMall
 */
public interface CategoryService extends IService<Category> {

    /**
     * 获取所有顶级分类
     * @return 分类列表
     */
    List<Category> getTopLevelCategories();

    /**
     * 获取子分类
     * @param parentId 父分类ID
     * @return 子分类列表
     */
    List<Category> getChildrenCategories(Long parentId);

    /**
     * 创建分类
     * @param category 分类信息
     * @return 分类实体
     */
    Category createCategory(Category category);

    /**
     * 更新分类
     * @param id 分类ID
     * @param category 分类信息
     * @return 是否成功
     */
    boolean updateCategory(Long id, Category category);

    /**
     * 删除分类
     * @param id 分类ID
     * @return 是否成功
     */
    boolean deleteCategory(Long id);
}