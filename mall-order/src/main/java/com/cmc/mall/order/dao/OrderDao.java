package com.cmc.mall.order.dao;

import com.cmc.mall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-08 14:40:48
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
