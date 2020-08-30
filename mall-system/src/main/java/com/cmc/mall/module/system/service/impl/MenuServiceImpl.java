package com.cmc.mall.module.system.service.impl;

import com.cmc.common.utils.Constant;
import com.cmc.mall.module.system.dao.MenuDao;
import com.cmc.mall.module.system.dao.UserDao;
import com.cmc.mall.module.system.entity.MenuEntity;
import com.cmc.mall.module.system.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cmc.common.utils.PageUtils;
import com.cmc.common.utils.Query;



@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuDao, MenuEntity> implements MenuService {

    @Autowired
    private MenuDao menuDao;
    @Autowired
    private UserDao userDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MenuEntity> page = this.page(
                new Query<MenuEntity>().getPage(params),
                new QueryWrapper<MenuEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<MenuEntity> getUserMenuList(Long userId) {
        //最高管理权限
        if (userId==Constant.SUPER_ADMIN){
          return getAllMenus(null);
        }
        List<Long> menuIds=getAllMenuIdsByUserId(userId);
        return getAllMenus(menuIds);
    }

    @Override
    public Set<String> getUserPermissions(Long userId) {
        List<String> permissions ;
        //最高管理权限
        if (userId== Constant.SUPER_ADMIN){
            permissions=new ArrayList<>();
            List<MenuEntity> menuEntities = menuDao.selectList(null);
            for (MenuEntity menuEntity : menuEntities) {
                permissions.add(menuEntity.getPerms());
            }
        }else {
            permissions = userDao.getUserPerms(userId);
        }
        //处理用户权限
        Set<String> permsSet=new HashSet<>();
        for (String permission : permissions) {
            if (StringUtils.isBlank(permission)){
                continue;
            }
            String[] split = permission.trim().split(",");
            permsSet.addAll(Arrays.asList(split));
        }
        return permsSet;
    }

    @Override
    public List<Long> getAllMenuIdsByUserId(Long userId) {
        return menuDao.getAllMenuIdsByUserId(userId);
    }

    /**
     * 根据菜单id获取菜单
     * @param menuIds
     * @return
     * author chengmingchao
     */
    private List<MenuEntity> getAllMenus(List<Long> menuIds) {
        List<MenuEntity> menuEntities = menuDao.selectBatchIds(menuIds);
        List<MenuEntity> allMenus=menuEntities.stream().filter((m)->m.getParentId()==0)
                .map((m)->{
                    m.setList(getChildMenus(m,menuEntities));
                    return m;
                })
                .collect(Collectors.toList());
        return allMenus;
    }

    /**
     * 递归查找子菜单
     * @param menuEntity
     * @param menuEntities
     * @return
     * author chengmingchao
     */
    private List<MenuEntity> getChildMenus(MenuEntity menuEntity, List<MenuEntity> menuEntities) {
        List<MenuEntity> childMenus=menuEntities.stream()
                .filter((m)->m.getParentId()==menuEntity.getMenuId())
                .map((m)->{
                    m.setList(getChildMenus(m,menuEntities));
                    return m;
                })
                .collect(Collectors.toList());
        return childMenus;
    }

}