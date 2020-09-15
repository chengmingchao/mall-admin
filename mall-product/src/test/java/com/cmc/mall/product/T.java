package com.cmc.mall.product;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.cmc.mall.product.entity.BrandEntity;
import com.cmc.mall.product.entity.CategoryBrandRelationEntity;
import com.cmc.mall.product.service.BrandService;
import com.cmc.mall.product.service.CategoryBrandRelationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author chengmingchao
 * @version 1.0
 * @date 2020/8/7 11:13 下午
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class T {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;
    @Test
    public void test(){
        List<CategoryBrandRelationEntity> byBrandId = categoryBrandRelationService.getByBrandId(5L);
        log.info("关联数据======>,{}",byBrandId);
    }
}
