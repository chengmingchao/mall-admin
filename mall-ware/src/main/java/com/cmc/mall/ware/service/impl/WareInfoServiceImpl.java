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

import com.cmc.mall.ware.dao.WareInfoDao;
import com.cmc.mall.ware.entity.WareInfoEntity;
import com.cmc.mall.ware.service.WareInfoService;


@Service("wareInfoService")
public class WareInfoServiceImpl extends ServiceImpl<WareInfoDao, WareInfoEntity> implements WareInfoService {

    @Autowired
    private WareInfoDao wareInfoDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WareInfoEntity> page = this.page(
                new Query<WareInfoEntity>().getPage(params),
                new QueryWrapper<WareInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<WareInfoEntity> getList(PageAndKeyParams pageAndKeyParams) {
        PageHelper.startPage(pageAndKeyParams.getPageNum(), pageAndKeyParams.getPageSize());
        List<WareInfoEntity> list=wareInfoDao.getList(pageAndKeyParams.getKey());
        return list;
    }

}