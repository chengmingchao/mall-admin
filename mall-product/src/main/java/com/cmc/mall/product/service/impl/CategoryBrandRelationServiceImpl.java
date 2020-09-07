package com.cmc.mall.product.service.impl;

import com.cmc.mall.product.dao.BrandDao;
import com.cmc.mall.product.dao.CategoryDao;
import com.cmc.mall.product.entity.BrandEntity;
import com.cmc.mall.product.entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cmc.common.utils.PageUtils;
import com.cmc.common.utils.Query;

import com.cmc.mall.product.dao.CategoryBrandRelationDao;
import com.cmc.mall.product.entity.CategoryBrandRelationEntity;
import com.cmc.mall.product.service.CategoryBrandRelationService;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

    @Autowired
    private BrandDao brandDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private CategoryBrandRelationDao categoryBrandRelationDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<CategoryBrandRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryBrandRelationEntity> getByBrandId(Long brandId) {
        return categoryBrandRelationDao.getByBrandId(brandId);
    }

    @Override
    public void saveRelation(CategoryBrandRelationEntity categoryBrandRelation) {
        Long brandId = categoryBrandRelation.getBrandId();
        Long catelogId = categoryBrandRelation.getCatelogId();
        BrandEntity brandEntity = brandDao.selectById(brandId);
        CategoryEntity categoryEntity = categoryDao.selectById(catelogId);
        categoryBrandRelation.setBrandName(brandEntity.getName());
        categoryBrandRelation.setCatelogName(categoryEntity.getName());
        categoryBrandRelationDao.insert(categoryBrandRelation);
    }

    @Override
    public void updateByCategory(Long catId, String catName) {
        categoryBrandRelationDao.updateByCategory(catId,catName);
    }

    @Override
    public void updateByBrand(Long brandId, String brandName) {
        categoryBrandRelationDao.updateByBrand(brandId,brandName);
    }
}