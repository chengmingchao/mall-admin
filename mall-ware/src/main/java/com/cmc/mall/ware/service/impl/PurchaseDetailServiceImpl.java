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

import com.cmc.mall.ware.dao.PurchaseDetailDao;
import com.cmc.mall.ware.entity.PurchaseDetailEntity;
import com.cmc.mall.ware.service.PurchaseDetailService;


@Service("purchaseDetailService")
public class PurchaseDetailServiceImpl extends ServiceImpl<PurchaseDetailDao, PurchaseDetailEntity> implements PurchaseDetailService {

    @Autowired
    private PurchaseDetailDao purchaseDetailDao;

    @Override
    public List<PurchaseDetailEntity> getList(PageAndKeyParams pageAndKeyParams,Integer status,Long wareId) {
        PageHelper.startPage(pageAndKeyParams.getPageNum(), pageAndKeyParams.getPageSize());
        List<PurchaseDetailEntity> list=purchaseDetailDao.getList(status,wareId,pageAndKeyParams.getKey());
        return list;
    }

}