package com.cmc.mall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.cmc.common.utils.CommonPage;
import com.cmc.mall.product.entity.PageAndKeyParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cmc.mall.product.entity.AttrEntity;
import com.cmc.mall.product.service.AttrService;
import com.cmc.common.utils.PageUtils;
import com.cmc.common.utils.R;



/**
 * 商品属性
 *
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-08 10:08:05
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;

    @GetMapping("/{attrType}/list/{catelogId}")
    public R baseList(@PathVariable("catelogId") Long catelogId, PageAndKeyParams pageAndKeyParams,
                      @PathVariable("attrType") String attrType){
        List<AttrEntity> list=attrService.getBaseAttrList(catelogId,pageAndKeyParams,attrType);
        return R.ok().put("data", CommonPage.restPage(list));
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:attr:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
    //@RequiresPermissions("product:attr:info")
    public R info(@PathVariable("attrId") Long attrId){
        AttrEntity attr=attrService.getbaseById(attrId);
        return R.ok().put("data", attr);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:attr:save")
    public R save(@RequestBody AttrEntity attr){
		attrService.saveAll(attr);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:attr:update")
    public R update(@RequestBody AttrEntity attr){
		attrService.updateAttr(attr);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:attr:delete")
    public R delete(@RequestBody Long[] attrIds){
		attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }

}
