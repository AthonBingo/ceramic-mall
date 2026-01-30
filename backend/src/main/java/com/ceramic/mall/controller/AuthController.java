package com.ceramic.mall.controller;

import com.ceramic.mall.entity.User;
import com.ceramic.mall.service.UserService;
import com.ceramic.mall.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证控制器
 * @author CeramicMall
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "认证管理", description = "用户认证相关API")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    @Operation(summary = "用户注册")
    public Result<User> register(@RequestBody User user) {
        try {
            User registeredUser = userService.register(user);
            return Result.success("注册成功", registeredUser);
        } catch (Exception e) {
            log.error("注册失败: {}", e.getMessage());
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/login")
    @Operation(summary = "用户登录")
    public Result<String> login(@RequestBody User user) {
        try {
            String token = userService.login(user.getUsername(), user.getPassword());
            return Result.success("登录成功", token);
        } catch (Exception e) {
            log.error("登录失败: {}", e.getMessage());
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/refresh")
    @Operation(summary = "刷新token")
    public Result<String> refresh(@RequestBody String token) {
        try {
            String newToken = userService.refreshToken(token);
            return Result.success("刷新成功", newToken);
        } catch (Exception e) {
            log.error("刷新token失败: {}", e.getMessage());
            return Result.error(e.getMessage());
        }
    }
}