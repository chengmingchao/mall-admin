package com.cmc.mall.module.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cmc.mall.module.system.entity.MenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单管理
 * 
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-27 22:27:55
 */
@Mapper
public interface MenuDao extends BaseMapper<MenuEntity> {

    List<Long> getAllMenuIdsByUserId(@Param("userId") Long userId);
	
}
