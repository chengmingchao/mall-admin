package com.cmc.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cmc.common.utils.PageUtils;
import com.cmc.mall.product.entity.BrandEntity;
import com.cmc.mall.product.entity.CategoryBrandRelationEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-08 10:08:05
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryBrandRelationEntity> getByBrandId(Long brandId);

    void saveRelation(CategoryBrandRelationEntity categoryBrandRelation);

    void updateByCategory(Long catId, String name);

    void updateByBrand(Long brandId, String name);

    List<BrandEntity> getBrandsByCatId(Long catId);
}

