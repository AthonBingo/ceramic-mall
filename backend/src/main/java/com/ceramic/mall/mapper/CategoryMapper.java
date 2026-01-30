package com.ceramic.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ceramic.mall.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 分类Mapper接口
 * @author CeramicMall
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 查询所有顶级分类
     * @return 分类列表
     */
    List<Category> selectTopLevelCategories();

    /**
     * 查询指定分类的子分类
     * @param parentId 父分类ID
     * @return 子分类列表
     */
    List<Category> selectByParentId(Long parentId);
}