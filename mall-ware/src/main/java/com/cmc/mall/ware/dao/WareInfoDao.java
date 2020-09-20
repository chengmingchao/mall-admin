package com.cmc.mall.ware.dao;

import com.cmc.mall.ware.entity.WareInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 仓库信息
 * 
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-08 14:46:49
 */
@Mapper
public interface WareInfoDao extends BaseMapper<WareInfoEntity> {

    List<WareInfoEntity> getList(@Param("key") String key);
}
