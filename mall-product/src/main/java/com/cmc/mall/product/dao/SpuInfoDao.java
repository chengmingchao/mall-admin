package com.cmc.mall.product.dao;

import com.cmc.mall.product.entity.SpuInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * spu信息
 * 
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-08 10:08:05
 */
@Mapper
public interface SpuInfoDao extends BaseMapper<SpuInfoEntity> {

    void saveSpuInfo(SpuInfoEntity spuInfoEntity);
}
