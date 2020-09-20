package com.cmc.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cmc.common.utils.PageAndKeyParams;
import com.cmc.common.utils.PageUtils;
import com.cmc.mall.ware.entity.PurchaseDetailEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-08 14:46:49
 */
public interface PurchaseDetailService extends IService<PurchaseDetailEntity> {

    List<PurchaseDetailEntity> getList(PageAndKeyParams pageAndKeyParams,Integer status,Long wareId);
}

