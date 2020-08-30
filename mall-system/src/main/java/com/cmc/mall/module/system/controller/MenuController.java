package com.cmc.mall.module.system.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cmc.mall.module.system.entity.MenuEntity;
import com.cmc.mall.module.system.service.MenuService;
import com.cmc.mall.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cmc.common.utils.PageUtils;
import com.cmc.common.utils.R;



/**
 * 菜单管理
 *
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-27 22:27:55
 */
@RestController
@RequestMapping("system/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    /**
     * 导航菜单
     */
    @GetMapping("/nav")
    public R nav(){
        Long userId = UserUtil.getuser().getUserId();
        List<MenuEntity> menuList = menuService.getUserMenuList(userId);    //获取用户菜单
        Set<String> permissions = menuService.getUserPermissions(userId);   //获取用户权限
        return R.ok().put("menuList", menuList).put("permissions", permissions);
    }
    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("system:menu:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = menuService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{menuId}")
    //@RequiresPermissions("system:menu:info")
    public R info(@PathVariable("menuId") Long menuId){
		MenuEntity menu = menuService.getById(menuId);

        return R.ok().put("menu", menu);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("system:menu:save")
    public R save(@RequestBody MenuEntity menu){
		menuService.save(menu);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("system:menu:update")
    public R update(@RequestBody MenuEntity menu){
		menuService.updateById(menu);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("system:menu:delete")
    public R delete(@RequestBody Long[] menuIds){
		menuService.removeByIds(Arrays.asList(menuIds));

        return R.ok();
    }

}
