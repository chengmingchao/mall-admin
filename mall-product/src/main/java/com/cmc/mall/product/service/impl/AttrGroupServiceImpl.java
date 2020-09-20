package com.cmc.mall.product.service.impl;

import com.cmc.mall.product.dao.AttrAttrgroupRelationDao;
import com.cmc.mall.product.dao.AttrDao;
import com.cmc.mall.product.entity.AttrEntity;
import com.cmc.common.utils.PageAndKeyParams;
import com.cmc.mall.product.service.AttrService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.cmc.mall.product.dao.AttrGroupDao;
import com.cmc.mall.product.entity.AttrGroupEntity;
import com.cmc.mall.product.service.AttrGroupService;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Autowired
    private AttrGroupDao attrGroupDao;

    @Autowired
    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Autowired
    private AttrDao attrDao;

    @Autowired
    private AttrService attrService;
    @Override
    public List<AttrGroupEntity> queryAttrGroupList(Long categoryId, PageAndKeyParams pageAndKeyParams) {
        PageHelper.startPage(pageAndKeyParams.getPageNum(),pageAndKeyParams.getPageSize());
        return attrGroupDao.selectListByCategoryId(categoryId,pageAndKeyParams.getKey());
    }

    @Override
    public List<AttrGroupEntity> getAttrGroupswithAttr(Long catelogId) {
        List<AttrGroupEntity> attrGroupEntities = attrGroupDao.selectListByCategoryId(catelogId, null);
        List<AttrGroupEntity> entities = attrGroupEntities.stream().map((e) -> {
            List<AttrEntity> attrRealation = attrService.getAttrRealation(e.getAttrGroupId());
            e.setAttrs(attrRealation);
            return e;
        }).collect(Collectors.toList());
        return entities;
    }
}