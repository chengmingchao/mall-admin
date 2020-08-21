package com.cmc.mall.product.controller;

import java.util.Arrays;
import java.util.List;

import com.cmc.common.utils.CommonPage;
import com.cmc.common.utils.ResultCode;
import com.cmc.common.valid.AddGroup;
import com.cmc.common.valid.UpdateGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.cmc.mall.product.entity.BrandEntity;
import com.cmc.mall.product.service.BrandService;
import com.cmc.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;


/**
 * 品牌
 *
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-08 10:08:05
 */
@Api(tags = "品牌相关接口")
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 列表
     */
    @ApiOperation("分页获取品牌信息接口")
    @PostMapping("/list")
    public R list(@RequestParam(value = "name",required = false) String name,
                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                  @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        List<BrandEntity> brandEntities = brandService.queryBrandList(name, pageNum, pageSize);
        return R.ok().put("data", CommonPage.restPage(brandEntities));
    }


    /**
     * 信息
     */
    @ApiOperation("根据id获取品牌信息")
    @GetMapping("/info/{brandId}")
    //@RequiresPermissions("product:brand:info")
    public R info(@PathVariable("brandId") Long brandId){
		BrandEntity brand = brandService.getById(brandId);
        return R.ok().put("data", brand);
    }

    /**
     * 保存
     */
    @ApiOperation("保存品牌信息")
    @PostMapping("/save")
    //@RequiresPermissions("product:brand:save")
    public R save(@Validated(AddGroup.class) @RequestBody BrandEntity brand){
            brandService.save(brand);
            return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:brand:update")
    public R update(@Validated(UpdateGroup.class) @RequestBody BrandEntity brand){
		brandService.updateById(brand);

        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation("根据id批量删除品牌")
    @PostMapping("/delete")
    public R delete(@RequestBody Long[] brandIds){
		brandService.removeByIds(Arrays.asList(brandIds));
        return R.ok();
    }


}
