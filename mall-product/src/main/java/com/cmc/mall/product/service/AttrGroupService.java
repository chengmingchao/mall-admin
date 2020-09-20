package com.cmc.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cmc.mall.product.entity.AttrGroupEntity;
import com.cmc.common.utils.PageAndKeyParams;

import java.util.List;

/**
 * 属性分组
 *
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-08 10:08:05
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    List<AttrGroupEntity> queryAttrGroupList(Long categoryId, PageAndKeyParams pageAndKeyParams);

    List<AttrGroupEntity> getAttrGroupswithAttr(Long catelogId);
}

