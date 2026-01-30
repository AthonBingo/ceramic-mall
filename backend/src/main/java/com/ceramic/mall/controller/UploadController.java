package com.ceramic.mall.controller;

import com.ceramic.mall.utils.FileUtil;
import com.ceramic.mall.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传控制器
 * @author CeramicMall
 */
@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
@Tag(name = "文件上传", description = "文件上传相关API")
public class UploadController {

    private final FileUtil fileUtil;

    @PostMapping("/image")
    @Operation(summary = "上传图片")
    public Result<String> uploadImage(@RequestParam("file") MultipartFile file,
                                      @RequestParam(defaultValue = "images") String type) {
        String imageUrl = fileUtil.saveFile(file, type);
        return Result.success(imageUrl);
    }

    @PostMapping("/avatar")
    @Operation(summary = "上传头像")
    public Result<String> uploadAvatar(@RequestParam("file") MultipartFile file) {
        String avatarUrl = fileUtil.saveFile(file, "avatars");
        return Result.success(avatarUrl);
    }
}