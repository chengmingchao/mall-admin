package com.cmc.mall.product.service.impl;

import com.cmc.mall.product.dao.AttrAttrgroupRelationDao;
import com.cmc.mall.product.dao.AttrGroupDao;
import com.cmc.mall.product.dao.CategoryDao;
import com.cmc.mall.product.entity.*;
import com.cmc.mall.product.service.AttrAttrgroupRelationService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
        attrAttrgroupRelationEntity.setAttrId(attr.getAttrId());
        attrAttrgroupRelationEntity.setAttrGroupId(attr.getAttrGroupId());
        attrAttrgroupRelationDao.insert(attrAttrgroupRelationEntity);
    }

    @Override
    public List<AttrEntity> getBaseAttrList(Long catelogId, PageAndKeyParams pageAndKeyParams) {
        PageHelper.startPage(pageAndKeyParams.getPageNum(),pageAndKeyParams.getPageSize());
        List<AttrEntity> baseAttr = attrDao.getBaseAttr(catelogId, pageAndKeyParams.getKey());
        List<AttrEntity> list=baseAttr.stream().map((b)->{
            CategoryEntity categoryEntity = categoryDao.selectById(b.getCatelogId());
            if (categoryEntity!=null){
                b.setCatelogName(categoryEntity.getName());
            }
            AttrAttrgroupRelationEntity byattrId = attrAttrgroupRelationDao.getByattrId(b.getAttrId());
            if (byattrId!=null){
                AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(byattrId.getAttrGroupId());
                b.setGroupName(attrGroupEntity.getAttrGroupName());
            }
            return b;
        }).collect(Collectors.toList());
        return list;
    }

    @Override
    public AttrEntity getbaseById(Long attrId) {
        AttrEntity attrEntity = this.getById(attrId);
        if (attrEntity!=null){
            Long[] catelogPath = categoryService.getCatelogPath(attrEntity.getCatelogId());
            attrEntity.setCatelogPath(catelogPath);
        }
        AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = attrAttrgroupRelationDao.getByattrId(attrId);
        if (attrAttrgroupRelationEntity!=null){
            attrEntity.setAttrGroupId(attrAttrgroupRelationEntity.getAttrGroupId());
        }
        return attrEntity;
    }

    @Transactional
    @Override
    public void updateAttr(AttrEntity attr) {
        this.updateById(attr);
        Integer count = attrAttrgroupRelationDao.selectCount(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attr.getAttrId()));
        AttrAttrgroupRelationEntity relationEntity=new AttrAttrgroupRelationEntity();
        relationEntity.setAttrGroupId(attr.getAttrGroupId());
        relationEntity.setAttrId(attr.getAttrId());
        if (count>0){
            attrAttrgroupRelationDao.updateAttrGroupId(relationEntity);
        }else {
            attrAttrgroupRelationDao.insert(relationEntity);
        }

    }
}