package com.cmc.mall.ware.service.impl;

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

import com.cmc.mall.ware.dao.WareSkuDao;
import com.cmc.mall.ware.entity.WareSkuEntity;
import com.cmc.mall.ware.service.WareSkuService;


@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {

    @Autowired
    private WareSkuDao wareSkuDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WareSkuEntity> page = this.page(
                new Query<WareSkuEntity>().getPage(params),
                new QueryWrapper<WareSkuEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<WareSkuEntity> getList(PageAndKeyParams pageAndKeyParams, Long skuId, Long wareId) {
        PageHelper.startPage(pageAndKeyParams.getPageNum(), pageAndKeyParams.getPageSize());
        List<WareSkuEntity> list=wareSkuDao.getList(skuId,wareId);
        return list;

    }

}