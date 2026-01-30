package com.ceramic.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ceramic.mall.entity.User;

/**
 * 用户服务接口
 * @author CeramicMall
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @param user 用户信息
     * @return 用户实体
     */
    User register(User user);

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    String login(String username, String password);

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户实体
     */
    User getUserByUsername(String username);

    /**
     * 更新用户信息
     * @param user 用户信息
     * @return 是否成功
     */
    boolean updateUserInfo(User user);

    /**
     * 修改密码
     * @param userId 用户ID
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 是否成功
     */
    boolean changePassword(Long userId, String oldPassword, String newPassword);

    /**
     * 上传头像
     * @param userId 用户ID
     * @param avatarUrl 头像URL
     * @return 是否成功
     */
    boolean uploadAvatar(Long userId, String avatarUrl);
}