package com.ceramic.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ceramic.mall.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户Mapper接口
 * @author CeramicMall
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户实体
     */
    User selectByUsername(String username);

    /**
     * 根据邮箱查询用户
     * @param email 邮箱
     * @return 用户实体
     */
    User selectByEmail(String email);
}