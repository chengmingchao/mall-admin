package com.cmc.mall.product.service.impl;

import com.cmc.common.utils.PageAndKeyParams;
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

import com.cmc.mall.product.dao.SkuInfoDao;
import com.cmc.mall.product.entity.SkuInfoEntity;
import com.cmc.mall.product.service.SkuInfoService;


@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {

    @Autowired
    private SkuInfoDao skuInfoDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                new QueryWrapper<SkuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<SkuInfoEntity> getList(PageAndKeyParams pageAndKeyParams,SkuInfoEntity skuInfoEntity) {
        PageHelper.startPage(pageAndKeyParams.getPageNum(), pageAndKeyParams.getPageSize());
        skuInfoEntity.setSkuName(pageAndKeyParams.getKey());
        List<SkuInfoEntity> list=skuInfoDao.getList(skuInfoEntity);
        return list;
    }
}