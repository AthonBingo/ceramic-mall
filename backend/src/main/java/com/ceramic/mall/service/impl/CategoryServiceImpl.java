package com.ceramic.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ceramic.mall.entity.Category;
import com.ceramic.mall.mapper.CategoryMapper;
import com.ceramic.mall.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 分类服务实现类
 * @author CeramicMall
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    private final CategoryMapper categoryMapper;

    @Override
    public List<Category> getTopLevelCategories() {
        return categoryMapper.selectTopLevelCategories();
    }

    @Override
    public List<Category> getChildrenCategories(Long parentId) {
        return categoryMapper.selectByParentId(parentId);
    }

    @Override
    public Category createCategory(Category category) {
        category.setCreateTime(LocalDateTime.now());
        save(category);
        return category;
    }

    @Override
    public boolean updateCategory(Long id, Category category) {
        category.setId(id);
        category.setUpdateTime(LocalDateTime.now());
        return updateById(category);
    }

    @Override
    public boolean deleteCategory(Long id) {
        return removeById(id);
    }
}
