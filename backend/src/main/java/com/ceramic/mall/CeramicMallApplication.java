package com.ceramic.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 陶瓷商城应用启动类
 * @author CeramicMall
 */
@SpringBootApplication
@MapperScan("com.ceramic.mall.mapper")
public class CeramicMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(CeramicMallApplication.class, args);
    }

}