package com.cmc.mall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.cmc.common.valid.AddGroup;
import com.cmc.common.valid.UpdateGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.cmc.mall.product.entity.CategoryEntity;
import com.cmc.mall.product.service.CategoryService;
import com.cmc.common.utils.PageUtils;
import com.cmc.common.utils.R;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 商品三级分类
 *
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-08 10:08:05
 */
@Api(tags = "商品分类接口")
@RestController
@RequestMapping("product/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 商品分类查询
     */
    @ApiOperation("查询全部商品分类接口")
    @GetMapping("/list")
    public R list(){
        List<CategoryEntity> list = categoryService.tree();
        return R.ok().put("data", list);
    }


    /**
     * 根据id查找分类信息
     */
    @GetMapping("/info/{catId}")
    @ApiOperation("根据id查找分类信息接口")
    @ApiImplicitParam(name = "catId",value = "分类id",defaultValue = "1",required = true)
    public R info(@PathVariable("catId") Long catId){
		CategoryEntity category = categoryService.getById(catId);
        return R.ok().put("category", category);
    }

    /**
     * 商品分类保存
     */
    @PostMapping("/save")
    @ApiOperation("保存商品分类接口")
    public R save(@Validated(AddGroup.class) @RequestBody CategoryEntity category){
        categoryService.save(category);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("修改商品分类接口")
    public R update(@Validated(UpdateGroup.class) @RequestBody CategoryEntity category){
		categoryService.updateById(category);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation("删除商品分类接口")
    public R delete(@RequestBody Long[] catIds){
        categoryService.deleteByIds(Arrays.asList(catIds));
        return R.ok();
    }

}
