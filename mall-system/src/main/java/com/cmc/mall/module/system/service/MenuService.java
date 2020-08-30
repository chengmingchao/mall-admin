package com.cmc.mall.module.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cmc.common.utils.PageUtils;
import com.cmc.mall.module.system.entity.MenuEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 菜单管理
 *
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-27 22:27:55
 */
public interface MenuService extends IService<MenuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<MenuEntity> getUserMenuList(Long userId);

    Set<String> getUserPermissions(Long userId);

    List<Long> getAllMenuIdsByUserId(Long userId);
}

