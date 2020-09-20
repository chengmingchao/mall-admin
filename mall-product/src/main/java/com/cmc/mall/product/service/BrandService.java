package com.cmc.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cmc.common.utils.PageUtils;
import com.cmc.mall.product.entity.BrandEntity;
import com.cmc.common.utils.PageAndKeyParams;

import java.util.List;
import java.util.Map;

/**
 * 品牌
 *
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-08 10:08:05
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<BrandEntity> queryBrandList(PageAndKeyParams pageAndKeyParams);

    void updateAllById(BrandEntity brand);
}

