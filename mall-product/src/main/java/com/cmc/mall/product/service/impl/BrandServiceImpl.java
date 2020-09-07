package com.cmc.mall.product.service.impl;

import com.cmc.mall.product.entity.PageAndKeyParams;
import com.cmc.mall.product.service.CategoryBrandRelationService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cmc.common.utils.PageUtils;
import com.cmc.common.utils.Query;

import com.cmc.mall.product.dao.BrandDao;
import com.cmc.mall.product.entity.BrandEntity;
import com.cmc.mall.product.service.BrandService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {
    @Autowired
    private BrandDao brandDao;
    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
                new QueryWrapper<BrandEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<BrandEntity> queryBrandList(PageAndKeyParams pageAndKeyParams) {
        PageHelper.startPage(pageAndKeyParams.getPageNum(),pageAndKeyParams.getPageSize());
        return brandDao.queryBrandList(pageAndKeyParams);
    }

    @Transactional
    @Override
    public void updateAllById(BrandEntity brand) {
        brandDao.updateById(brand);
        if (!StringUtils.isEmpty(brand.getName())){
            categoryBrandRelationService.updateByBrand(brand.getBrandId(),brand.getName());
            //TODO 更新其他地方引用的品牌信息
        }
    }

}