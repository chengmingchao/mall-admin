package com.cmc.mall.module.system.controller;

import java.util.Arrays;
import java.util.Map;

import com.cmc.mall.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cmc.mall.module.system.entity.UserEntity;
import com.cmc.mall.module.system.service.UserService;
import com.cmc.common.utils.PageUtils;
import com.cmc.common.utils.R;



/**
 * 后台用户表
 *
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-17 21:05:52
 */
@RestController
@RequestMapping("system/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("system:user:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryPage(params);
        return R.ok().put("data", page);
    }

    /**
     * 获取登录的用户信息
     */
    @GetMapping("/info")
    public R info(){
        UserEntity userEntity=UserUtil.getuser();
        return R.ok().put("user", UserUtil.getuser());
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("system:user:info")
    public R info(@PathVariable("id") Long id){
		UserEntity user = userService.getById(id);

        return R.ok().put("user", user);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("system:user:save")
    public R save(@RequestBody UserEntity user){
		userService.save(user);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("system:user:update")
    public R update(@RequestBody UserEntity user){
		userService.updateById(user);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("system:user:delete")
    public R delete(@RequestBody Long[] ids){
		userService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
