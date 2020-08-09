package com.cmc.mall.coupon.dao;

import com.cmc.mall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-08 09:56:54
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
