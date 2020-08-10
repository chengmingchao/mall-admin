package com.cmc.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author chengmingchao
 * @version 1.0
 * @date 2020/8/10 8:46 下午
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
public class MallGateway {
    public static void main(String[] args) {
        SpringApplication.run(MallGateway.class,args);
    }
}
