package com.cmc.mall.product.dao;

import com.cmc.mall.product.entity.SkuInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * sku信息
 * 
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-08 10:08:05
 */
@Mapper
public interface SkuInfoDao extends BaseMapper<SkuInfoEntity> {

    List<SkuInfoEntity> getList(SkuInfoEntity skuInfoEntity);
}
