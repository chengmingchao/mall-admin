package com.cmc.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cmc.common.utils.PageAndKeyParams;
import com.cmc.mall.product.entity.SpuInfoEntity;
import com.cmc.mall.product.vo.SpuVO;

import java.util.List;

/**
 * spu信息
 *
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-08 10:08:05
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    void saveSpuInfo(SpuVO spuVO);

    List<SpuInfoEntity> getList(PageAndKeyParams pageAndKeyParams,SpuInfoEntity spuInfoEntity);
}

