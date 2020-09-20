package com.cmc.mall.ware.dao;

import com.cmc.mall.ware.entity.PurchaseDetailEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * 
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-08 14:46:49
 */
@Mapper
public interface PurchaseDetailDao extends BaseMapper<PurchaseDetailEntity> {

    List<PurchaseDetailEntity> getList(@Param("status") Integer status,@Param("wareId") Long wareId,@Param("key") String key);
}
