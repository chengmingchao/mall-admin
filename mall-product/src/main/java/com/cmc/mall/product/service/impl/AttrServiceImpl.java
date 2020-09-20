package com.cmc.mall.product.service.impl;

import com.cmc.common.utils.PageAndKeyParams;
import com.cmc.mall.product.dao.AttrAttrgroupRelationDao;
import com.cmc.mall.product.dao.AttrGroupDao;
import com.cmc.mall.product.dao.CategoryDao;
import com.cmc.mall.product.entity.*;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cmc.common.utils.PageUtils;
import com.cmc.common.utils.Query;

import com.cmc.mall.product.dao.AttrDao;
import com.cmc.mall.product.service.AttrService;
import org.springframework.transaction.annotation.Transactional;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Autowired
    private AttrDao attrDao;

    @Autowired
    private AttrGroupDao attrGroupDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CategoryServiceImpl categoryService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void saveAll(AttrEntity attr) {
        this.save(attr);
        if (attr.getAttrType() == 1) {
            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
            attrAttrgroupRelationEntity.setAttrId(attr.getAttrId());
            attrAttrgroupRelationEntity.setAttrGroupId(attr.getAttrGroupId());
            attrAttrgroupRelationDao.insert(attrAttrgroupRelationEntity);
        }
    }

    @Override
    public List<AttrEntity> getBaseAttrList(Long catelogId, PageAndKeyParams pageAndKeyParams, String attrType) {
        PageHelper.startPage(pageAndKeyParams.getPageNum(), pageAndKeyParams.getPageSize());
        String type = "base".equalsIgnoreCase(attrType) ? "1" : "0";
        List<AttrEntity> baseAttr = attrDao.getBaseAttr(catelogId, pageAndKeyParams.getKey(), type);
        String finalAttrType = attrType;
        List<AttrEntity> list = baseAttr.stream().map((b) -> {
            CategoryEntity categoryEntity = categoryDao.selectById(b.getCatelogId());
            if (categoryEntity != null) {
                b.setCatelogName(categoryEntity.getName());
            }
            if ("base".equalsIgnoreCase(finalAttrType)) {
                AttrAttrgroupRelationEntity byattrId = attrAttrgroupRelationDao.getByattrId(b.getAttrId());
                if (byattrId != null) {
                    AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(byattrId.getAttrGroupId());
                    b.setGroupName(attrGroupEntity.getAttrGroupName());
                }
            }

            return b;
        }).collect(Collectors.toList());
        return list;
    }

    @Override
    public AttrEntity getbaseById(Long attrId) {
        AttrEntity attrEntity = this.getById(attrId);
        if (attrEntity != null) {
            Long[] catelogPath = categoryService.getCatelogPath(attrEntity.getCatelogId());
            attrEntity.setCatelogPath(catelogPath);
        }
        if (attrEntity.getAttrType() == 1) {
            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = attrAttrgroupRelationDao.getByattrId(attrId);
            if (attrAttrgroupRelationEntity != null) {
                attrEntity.setAttrGroupId(attrAttrgroupRelationEntity.getAttrGroupId());
            }
        }
        return attrEntity;
    }

    @Transactional
    @Override
    public void updateAttr(AttrEntity attr) {
        this.updateById(attr);
        if (attr.getAttrType() == 1) {
            Integer count = attrAttrgroupRelationDao.selectCount(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attr.getAttrId()));
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            relationEntity.setAttrGroupId(attr.getAttrGroupId());
            relationEntity.setAttrId(attr.getAttrId());
            if (count > 0) {
                attrAttrgroupRelationDao.updateAttrGroupId(relationEntity);
            } else {
                attrAttrgroupRelationDao.insert(relationEntity);
            }
        }
    }

    @Override
    public List<AttrEntity> getAttrRealation(Long attrgroupId) {
        List<AttrAttrgroupRelationEntity> relationEntities=attrAttrgroupRelationDao.getByattrGroupId(attrgroupId);
        List<Long> ids = relationEntities.stream().map((e) -> {
            return e.getAttrId();
        }).collect(Collectors.toList());
        if (ids==null||ids.size()==0){
            return null;
        }
        List<AttrEntity> attrEntities = attrDao.selectBatchIds(ids);
        return attrEntities;
    }

    @Override
    public void deleteRelation(AttrAttrgroupRelationEntity[] relationEntities) {
        List<AttrAttrgroupRelationEntity> collect = Arrays.stream(relationEntities).collect(Collectors.toList());
        attrAttrgroupRelationDao.deleteRelation(collect);
    }

    @Override
    public List<AttrEntity> getNoattrRelation(Long attrgroupId,PageAndKeyParams pageAndKeyParams) {
        List<AttrAttrgroupRelationEntity> attrgroupRelationEntities = attrAttrgroupRelationDao.getByattrGroupId(attrgroupId);
        List<Long> attrIds = attrgroupRelationEntities.stream().map((e) ->
                e.getAttrId()).collect(Collectors.toList());
        List<AttrEntity> attrEntities;
        if (attrIds.size()==0||attrIds==null){
            attrEntities = attrDao.getBaseAttr(null,pageAndKeyParams.getKey(),null);
        }else {
            attrEntities = attrDao.getNoattrRelation(attrIds,pageAndKeyParams.getKey());
        }
        return attrEntities;
    }

    @Override
    public void saveAttrRelation(AttrAttrgroupRelationEntity[] relationEntities) {
        List<AttrAttrgroupRelationEntity> attrgroupRelationEntities = Arrays.stream(relationEntities).collect(Collectors.toList());
        attrAttrgroupRelationDao.batchSaveAttrRelation(attrgroupRelationEntities);
    }
}