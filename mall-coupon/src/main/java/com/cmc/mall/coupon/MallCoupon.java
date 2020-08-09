package com.cmc.mall.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author chengmingchao
 * @version 1.0
 * @date 2020/8/8 1:59 下午
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MallCoupon {
    public static void main(String[] args) {
        SpringApplication.run(MallCoupon.class,args);
    }
}
