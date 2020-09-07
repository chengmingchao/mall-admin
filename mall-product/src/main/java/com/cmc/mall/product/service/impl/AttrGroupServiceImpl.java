package com.cmc.mall.product.service.impl;

import com.cmc.mall.product.entity.PageAndKeyParams;
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

import com.cmc.mall.product.dao.AttrGroupDao;
import com.cmc.mall.product.entity.AttrGroupEntity;
import com.cmc.mall.product.service.AttrGroupService;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Autowired
    private AttrGroupDao attrGroupDao;

    @Override
    public List<AttrGroupEntity> queryAttrGroupList(Long categoryId, PageAndKeyParams pageAndKeyParams) {
        PageHelper.startPage(pageAndKeyParams.getPageNum(),pageAndKeyParams.getPageSize());
        return attrGroupDao.selectListByCategoryId(categoryId,pageAndKeyParams.getKey());
    }

}