package com.cmc.mall.system.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmc.mall.system.entity.UserLoginLogEntity;
import com.cmc.mall.system.service.UserLoginLogService;
import com.cmc.common.utils.PageUtils;
import com.cmc.common.utils.R;



/**
 * 后台用户登录日志表
 *
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-17 21:05:52
 */
@RestController
@RequestMapping("system/userloginlog")
public class UserLoginLogController {
    @Autowired
    private UserLoginLogService userLoginLogService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("system:userloginlog:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userLoginLogService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("system:userloginlog:info")
    public R info(@PathVariable("id") Long id){
		UserLoginLogEntity userLoginLog = userLoginLogService.getById(id);

        return R.ok().put("userLoginLog", userLoginLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("system:userloginlog:save")
    public R save(@RequestBody UserLoginLogEntity userLoginLog){
		userLoginLogService.save(userLoginLog);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("system:userloginlog:update")
    public R update(@RequestBody UserLoginLogEntity userLoginLog){
		userLoginLogService.updateById(userLoginLog);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("system:userloginlog:delete")
    public R delete(@RequestBody Long[] ids){
		userLoginLogService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
