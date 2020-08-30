package com.cmc.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author chengmingchao
 * @version 1.0
 * @date 2020/8/30 8:13 下午
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class UploadMain {
    public static void main(String[] args) {
        SpringApplication.run(UploadMain.class,args);
    }
}
