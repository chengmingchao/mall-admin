package com.cmc.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cmc.common.utils.PageUtils;
import com.cmc.mall.product.entity.AttrGroupEntity;
import com.cmc.mall.product.entity.PageAndKeyParams;

import java.util.List;
import java.util.Map;

/**
 * 属性分组
 *
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-08 10:08:05
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    List<AttrGroupEntity> queryAttrGroupList(Long categoryId, PageAndKeyParams pageAndKeyParams);
}

