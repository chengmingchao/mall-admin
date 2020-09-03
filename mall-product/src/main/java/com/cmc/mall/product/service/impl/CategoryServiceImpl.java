package com.cmc.mall.product.service.impl;

import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cmc.common.utils.PageUtils;
import com.cmc.common.utils.Query;

import com.cmc.mall.product.dao.CategoryDao;
import com.cmc.mall.product.entity.CategoryEntity;
import com.cmc.mall.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> tree() {
        List<CategoryEntity> allCategory = baseMapper.selectList(null); //查询所有分类
        List<CategoryEntity> firstCategory = allCategory.stream()
                .filter(e -> e.getParentCid() == 0)
                .map(e -> {
                    e.setChildren(getChildrens(e, allCategory));
                    return e;
                })
                .sorted((e1, e2) ->
                        Integer.compare(e1.getSort() == null ? 0 : e1.getSort(), (e2.getSort() == null ? 0 : e2.getSort()))
                )
                .collect(Collectors.toList());  //过滤得到一级分类
        return firstCategory;
    }


    /**
     * 递归查找子菜单
     *
     * @param categoryEntity
     * @param allCategory
     * @return
     */
    public List<CategoryEntity> getChildrens(CategoryEntity categoryEntity, List<CategoryEntity> allCategory) {
        List<CategoryEntity> childrenCategory = allCategory.stream()
                .filter(e -> e.getParentCid() == categoryEntity.getCatId())
                .map(e -> {
                    e.setChildren(getChildrens(e, allCategory)); //递归查找
                    return e;
                })
                .sorted((e1, e2) -> Integer.compare(e1.getSort() == null ? 0 : e1.getSort(), (e2.getSort() == null ? 0 : e2.getSort())))
                .collect(Collectors.toList());

        return childrenCategory;
    }

    @Override
    public void deleteByIds(List<Long> asList) {
        //TODO 检查当前删除的菜单是否有其他地方引用
        baseMapper.deleteBatchIds(asList);
    }

    @Override
    public Long[] getCatelogPath(Long catelogId) {
        List<Long> pathList=new ArrayList<>();
        List<Long> parentPath = getParentPath(catelogId, pathList);
        Collections.reverse(parentPath);
        return parentPath.toArray(new Long[parentPath.size()]);
    }

    private List<Long> getParentPath(Long catelogId,List<Long> pathList){
        pathList.add(catelogId);
        CategoryEntity categoryEntity = baseMapper.selectById(catelogId);
        if (categoryEntity.getParentCid()!=0){
            getParentPath(categoryEntity.getParentCid(),pathList);
        }
        return pathList;
    }

}