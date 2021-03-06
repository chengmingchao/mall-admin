package com.cmc.mall.product.dao;

import com.cmc.mall.product.entity.SpuImagesEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * spu图片
 * 
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-08 10:08:05
 */
@Mapper
public interface SpuImagesDao extends BaseMapper<SpuImagesEntity> {

    void saveSpuImages(@Param("spuImagesEntities") List<SpuImagesEntity> spuImagesEntities);
}
