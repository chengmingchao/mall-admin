package com.cmc.mall.product.controller;

import com.cmc.common.utils.R;
import com.cmc.common.utils.ResultCode;
import io.minio.MinioClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;

/**
 * @author chengmingchao
 * @version 1.0
 * @date 2020/8/20 8:28 下午
 * minio文件存储管理
 */
@Api(tags = "文件上传接口")
@Slf4j
@RestController
public class MinioController {
    @Value("${minio.url}")
    private  String url;  //minio服务的IP端口
    @Value("${minio.accessKey}")
    private  String accessKey;
    @Value("${minio.secretKey}")
    private  String secretKey;
    private  String bucketName="file";
    private  MinioClient minioClient;

    /**
     * 初始化minio
     */
    @PostConstruct
    public void init(){
        try {
            minioClient = new MinioClient(url, accessKey, secretKey);
        } catch (Exception e) {
            log.error("初始化MinioClient失败", e);
            throw new RuntimeException("初始化MinioClient失败", e);
        }
        boolean found = false;
        try {
            found = minioClient.bucketExists(bucketName);
        } catch (Exception e) {
            log.error("查询bucket失败", e);
            throw new RuntimeException("查询bucket失败", e);
        }
        if (!found) {
            try {
                minioClient.makeBucket(bucketName);
            } catch (Exception e) {
                log.error("初始化bucket失败", e);
                throw new RuntimeException("初始化bucket失败", e);
            }
        }
    }

    /**
     * 文件上传并返回文件地址
     */
    @ApiOperation(value="文件上传接口")
    @PostMapping(value = "/uploadFile",headers="content-type=multipart/form-data")
    public R uploadFile (@RequestParam("file") MultipartFile file){
        try {
            String fileName = file.getOriginalFilename();
            String fileUrl = minioClient.presignedGetObject(bucketName, fileName);  //获取文件下载地址
            String fileContentType = file.getContentType();
            minioClient.putObject(bucketName, fileName, file.getInputStream(),file.getInputStream().available(), fileContentType);
            return R.ok().put("data",fileUrl);
        } catch (Exception e) {
           return R.error(ResultCode.UPLOAD_FAILED.getCode(),ResultCode.UPLOAD_FAILED.getMessage()).put("data",e.getMessage());
        }
    }
}
