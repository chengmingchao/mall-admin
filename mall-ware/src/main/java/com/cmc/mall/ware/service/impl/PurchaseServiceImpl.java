package com.cmc.mall.ware.service.impl;

import com.cmc.common.constant.WareConstant;
import com.cmc.mall.ware.entity.MergeVo;
import com.cmc.mall.ware.entity.PurchaseDetailEntity;
import com.cmc.mall.ware.service.PurchaseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cmc.common.utils.PageUtils;
import com.cmc.common.utils.Query;

import com.cmc.mall.ware.dao.PurchaseDao;
import com.cmc.mall.ware.entity.PurchaseEntity;
import com.cmc.mall.ware.service.PurchaseService;
import org.springframework.transaction.annotation.Transactional;


@Service("purchaseService")
public class PurchaseServiceImpl extends ServiceImpl<PurchaseDao, PurchaseEntity> implements PurchaseService {

    @Autowired
    private PurchaseDao purchaseDao;
    @Autowired
    private PurchaseDetailService purchaseDetailService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PurchaseEntity> page = this.page(
                new Query<PurchaseEntity>().getPage(params),
                new QueryWrapper<PurchaseEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<PurchaseEntity> getUnreceiveList() {
        List<PurchaseEntity> list=purchaseDao.getUnreceiveList();
        return list;
    }

    @Transactional
    @Override
    public void merge(MergeVo mergeVo) {
        Long purchaseId = mergeVo.getPurchaseId();
        if (purchaseId==null){
            //自动创建新单
            PurchaseEntity purchaseEntity=new PurchaseEntity();
            purchaseEntity.setStatus(WareConstant.PurchaseStatus.CREATED.getCode());
            purchaseEntity.setCreateTime(new Date());
            purchaseEntity.setUpdateTime(new Date());
            this.save(purchaseEntity);
            purchaseId = purchaseEntity.getId();
        }

        List<Long> item = mergeVo.getItems();
        Long finalPurchaseId = purchaseId;
        List<PurchaseDetailEntity> collect = item.stream().map(i -> {
            PurchaseDetailEntity entity = new PurchaseDetailEntity();
            entity.setId(i);
            entity.setPurchaseId(finalPurchaseId);
            entity.setStatus(WareConstant.PurchaseDetailStatus.ASSIGNED.getCode());
            return entity;
        }).collect(Collectors.toList());

        purchaseDetailService.updateBatchById(collect);

        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setId(purchaseId);
        purchaseEntity.setUpdateTime(new Date());
        this.updateById(purchaseEntity);
    }

}