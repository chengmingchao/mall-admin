package com.cmc.mall.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author chengmingchao
 * @version 1.0
 * @date 2020/8/8 2:21 下午
 */
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class MallMember {
    public static void main(String[] args) {
        SpringApplication.run(MallMember.class,args);
    }
}
