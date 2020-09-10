package com.cmc.mall.product.dao;

import com.cmc.mall.product.entity.CategoryBrandRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 品牌分类关联
 * 
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-08 10:08:05
 */
@Mapper
public interface CategoryBrandRelationDao extends BaseMapper<CategoryBrandRelationEntity> {
	List<CategoryBrandRelationEntity> getByBrandId(@Param("brandId") Long brandId);

	void updateByCategory(@Param("catId") Long catId,@Param("catName") String catName);

    void updateByBrand(@Param("brandId") Long brandId,@Param("brandName") String brandName);

    List<Long> getBrandIdsBycatId(@Param("catId") Long catId);
}
