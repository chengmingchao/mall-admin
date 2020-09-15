package com.cmc.mall.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author chengmingchao
 * @version 1.0
 * @date 2020/8/8 2:15 下午
 */
@EnableFeignClients(basePackages = "com.cmc.mall.product.feign")
@SpringBootApplication
public class MallProduct {
    public static void main(String[] args) {
        SpringApplication.run(MallProduct.class,args);
    }
}
