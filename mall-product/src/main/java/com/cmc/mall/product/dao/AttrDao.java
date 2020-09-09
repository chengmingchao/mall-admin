package com.cmc.mall.product.dao;

import com.cmc.mall.product.entity.AttrEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品属性
 * 
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-08 10:08:05
 */
@Mapper
public interface AttrDao extends BaseMapper<AttrEntity> {

    List<AttrEntity> getBaseAttr(@Param("catelogId") Long catelogId,@Param("key") String key,@Param("attrType") String attrType);

    List<AttrEntity> getNoattrRelation(@Param("attrIds") List<Long> attrIds,@Param("key") String key);
}
