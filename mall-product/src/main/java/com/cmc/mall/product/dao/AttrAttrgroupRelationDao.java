package com.cmc.mall.product.dao;

import com.cmc.mall.product.entity.AttrAttrgroupRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 属性&属性分组关联
 * 
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-08 10:08:05
 */
@Mapper
public interface AttrAttrgroupRelationDao extends BaseMapper<AttrAttrgroupRelationEntity> {

	AttrAttrgroupRelationEntity getByattrId(@Param("attrId") Long attrId);

	void updateAttrGroupId(AttrAttrgroupRelationEntity relationEntity);

    List<AttrAttrgroupRelationEntity> getByattrGroupId(@Param("attrgroupId") Long attrgroupId);

    void deleteRelation(@Param("entities") List<AttrAttrgroupRelationEntity> entities);

    void batchSaveAttrRelation(@Param("attrgroupRelationEntities") List<AttrAttrgroupRelationEntity> attrgroupRelationEntities);
}
