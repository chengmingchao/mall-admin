package com.cmc.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cmc.common.utils.PageUtils;
import com.cmc.mall.product.entity.AttrAttrgroupRelationEntity;
import com.cmc.mall.product.entity.AttrEntity;
import com.cmc.common.utils.PageAndKeyParams;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-08 10:08:05
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAll(AttrEntity attr);

    List<AttrEntity> getBaseAttrList(Long catelogId, PageAndKeyParams pageAndKeyParams,String attrType);

    AttrEntity getbaseById(Long attrId);

    void updateAttr(AttrEntity attr);

    List<AttrEntity> getAttrRealation(Long attrgroupId);

    void deleteRelation(AttrAttrgroupRelationEntity[] relationEntities);

    List<AttrEntity> getNoattrRelation(Long attrgroupId,PageAndKeyParams pageAndKeyParams);

    void saveAttrRelation(AttrAttrgroupRelationEntity[] relationEntities);
}

