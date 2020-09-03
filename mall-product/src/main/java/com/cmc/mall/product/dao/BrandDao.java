package com.cmc.mall.product.dao;

import com.cmc.mall.product.entity.BrandEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cmc.mall.product.entity.PageAndKeyParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 品牌
 * 
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-08 10:08:05
 */
@Mapper
public interface BrandDao extends BaseMapper<BrandEntity> {

    List<BrandEntity> queryBrandList(PageAndKeyParams pageAndKeyParams);
}
