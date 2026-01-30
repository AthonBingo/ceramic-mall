package com.ceramic.mall.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * 文件处理工具类
 * @author CeramicMall
 */
@Component
@Slf4j
public class FileUtil {

    @Value("${file.upload.path}")
    private String uploadPath;

    /**
     * 保存文件
     * @param file 文件
     * @param type 文件类型目录（如：products, avatars等）
     * @return 文件访问URL
     */
    public String saveFile(MultipartFile file, String type) {
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("文件不能为空");
        }

        // 生成文件名
        String originalFilename = file.getOriginalFilename();
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String fileName = UUID.randomUUID().toString().replace("-", "") + extension;

        // 按日期分目录存储
        String datePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String savePath = uploadPath + type + "/" + datePath + "/";
        File directory = new File(savePath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 保存文件
        String filePath = savePath + fileName;
        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            log.error("保存文件失败: {}", e.getMessage());
            throw new RuntimeException("保存文件失败");
        }

        // 返回访问URL
        return "/uploads/" + type + "/" + datePath + "/" + fileName;
    }

    /**
     * 删除文件
     * @param filePath 文件路径
     * @return 是否成功
     */
    public boolean deleteFile(String filePath) {
        try {
            File file = new File(uploadPath + filePath);
            if (file.exists()) {
                return file.delete();
            }
            return true;
        } catch (Exception e) {
            log.error("删除文件失败: {}", e.getMessage());
            return false;
        }
    }

    /**
     * 判断文件类型是否是图片
     * @param file 文件
     * @return 是否是图片
     */
    public boolean isImage(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return false;
        }

        String contentType = file.getContentType();
        if (contentType == null) {
            return false;
        }

        return contentType.startsWith("image/");
    }

    /**
     * 获取文件扩展名
     * @param file 文件
     * @return 扩展名
     */
    public String getFileExtension(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null && originalFilename.contains(".")) {
            return originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        return "";
    }

    /**
     * 判断文件大小是否超过限制
     * @param file 文件
     * @param maxSize 最大字节数
     * @return 是否超过限制
     */
    public boolean isFileTooLarge(MultipartFile file, long maxSize) {
        if (file == null || file.isEmpty()) {
            return false;
        }
        return file.getSize() > maxSize;
    }
}