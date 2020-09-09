package com.cmc.mall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.cmc.common.utils.CommonPage;
import com.cmc.mall.product.entity.AttrAttrgroupRelationEntity;
import com.cmc.mall.product.entity.AttrEntity;
import com.cmc.mall.product.entity.PageAndKeyParams;
import com.cmc.mall.product.service.AttrService;
import com.cmc.mall.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cmc.mall.product.entity.AttrGroupEntity;
import com.cmc.mall.product.service.AttrGroupService;
import com.cmc.common.utils.PageUtils;
import com.cmc.common.utils.R;



/**
 * 属性分组
 *
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-08 10:08:05
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AttrService attrService;

    @PostMapping("attr/relation")
    public R attrRelation(@RequestBody AttrAttrgroupRelationEntity[] relationEntities){
        attrService.saveAttrRelation(relationEntities);
        return R.ok();
    }
    //this.attrGroupId + "/noattr/relation"
    @GetMapping("/{attrgroupId}/noattr/relation")
    public R noattrRelation(@PathVariable("attrgroupId") Long attrgroupId, PageAndKeyParams pageAndKeyParams){
        List<AttrEntity> list=attrService.getNoattrRelation(attrgroupId,pageAndKeyParams);
        return R.ok().put("data",CommonPage.restPage(list));
    }

    @GetMapping("/{attrgroupId}/attr/relation")
    public R attrRelation(@PathVariable("attrgroupId") Long attrgroupId) {
        List<AttrEntity> attrEntities=attrService.getAttrRealation(attrgroupId);
        return R.ok().put("data",attrEntities);
    }

    /**
     * 删除属性关联
     * @param relationEntities
     * @return
     */
    @PostMapping("/attr/relation/delete")
    public R deleteRelation(@RequestBody AttrAttrgroupRelationEntity[] relationEntities){
        attrService.deleteRelation(relationEntities);
        return R.ok();
    }
    /**
     * 列表
     */
    @RequestMapping("/list/{categoryId}")
    //@RequiresPermissions("product:attrgroup:list")
    public R list(@PathVariable("categoryId") Long categoryId,
                  PageAndKeyParams pageAndKeyParams){
        List<AttrGroupEntity> list=attrGroupService.queryAttrGroupList(categoryId,pageAndKeyParams);
        return R.ok().put("data",  CommonPage.restPage(list));
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    //@RequiresPermissions("product:attrgroup:info")
    public R info(@PathVariable("attrGroupId") Long attrGroupId){
		AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);
        Long catelogId = attrGroup.getCatelogId();
        Long[] path=categoryService.getCatelogPath(catelogId);
        attrGroup.setCatelogPath(path);
        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:attrgroup:save")
    public R save(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:attrgroup:update")
    public R update(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:attrgroup:delete")
    public R delete(@RequestBody Long[] attrGroupIds){
		attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

}
