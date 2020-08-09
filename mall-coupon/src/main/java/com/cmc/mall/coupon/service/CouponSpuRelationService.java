package com.cmc.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cmc.common.utils.PageUtils;
import com.cmc.mall.coupon.entity.CouponSpuRelationEntity;

import java.util.Map;

/**
 * 优惠券与产品关联
 *
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-08 09:56:54
 */
public interface CouponSpuRelationService extends IService<CouponSpuRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

