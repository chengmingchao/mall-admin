package com.cmc.mall.product.service.impl;

import com.cmc.common.to.SkuReductionTo;
import com.cmc.common.to.SpuBoundsTo;
import com.cmc.common.utils.R;
import com.cmc.mall.product.dao.*;
import com.cmc.mall.product.entity.*;
import com.cmc.mall.product.feign.CouponFeignService;
import com.cmc.mall.product.service.*;
import com.cmc.mall.product.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cmc.common.utils.PageUtils;
import com.cmc.common.utils.Query;

import org.springframework.transaction.annotation.Transactional;


@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {

    @Autowired
    private SpuInfoDao spuInfoDao;
    @Autowired
    private SpuInfoDescDao spuInfoDescDao;
    @Autowired
    private SpuImagesDao spuImagesDao;
    @Autowired
    private AttrDao attrDao;
    @Autowired
    private ProductAttrValueService productAttrValueService;
    @Autowired
    private SkuInfoService skuInfoService;
    @Autowired
    private SkuImagesService skuImagesService;
    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;

    @Autowired
    private CouponFeignService couponFeignService;
    @Transactional
    @Override
    public void saveSpuInfo(SpuVO spuVO) {
        //1.保存spu基本信息   pms_spu_info
        SpuInfoEntity spuInfoEntity = new SpuInfoEntity();
        BeanUtils.copyProperties(spuVO, spuInfoEntity);
        spuInfoDao.saveSpuInfo(spuInfoEntity);

        //2.保存spu图片描述   pms_spu_info_desc
        List<String> decript = spuVO.getDecript();
        SpuInfoDescEntity infoDescEntity = new SpuInfoDescEntity();
        infoDescEntity.setSpuId(spuInfoEntity.getId());
        infoDescEntity.setDecript(String.join(",", decript));
        spuInfoDescDao.saveSpuInfoDesc(infoDescEntity);

        //3.保存spu图片集    pms_spu_images
        List<String> images = spuVO.getImages();
        if (images == null || images.size() == 0) {
        } else {
            List<SpuImagesEntity> imagesEntities = images.stream().map((m) -> {
                SpuImagesEntity imagesEntity = new SpuImagesEntity();
                imagesEntity.setSpuId(spuInfoEntity.getId());
                imagesEntity.setImgUrl(m);
                return imagesEntity;
            }).collect(Collectors.toList());
            spuImagesDao.saveSpuImages(imagesEntities);
        }


        //4.保存spu的规格参数  pms_product_attr_value
        List<BaseAttrs> baseAttrs = spuVO.getBaseAttrs();
        List<ProductAttrValueEntity> valueEntities = baseAttrs.stream().map((attr) -> {
            ProductAttrValueEntity productAttrValueEntity = new ProductAttrValueEntity();
            productAttrValueEntity.setAttrId(attr.getAttrId());
            AttrEntity attrEntity = attrDao.selectById(attr.getAttrId());
            productAttrValueEntity.setAttrName(attrEntity.getAttrName());
            productAttrValueEntity.setSpuId(spuInfoEntity.getId());
            productAttrValueEntity.setAttrValue(attr.getAttrValue());
            productAttrValueEntity.setQuickShow(attr.getShowDesc());
            return productAttrValueEntity;
        }).collect(Collectors.toList());
        productAttrValueService.saveBatch(valueEntities);

        //5.保存spu的积分信息  mall_sms->sms_spu_bounds
        Bounds bounds = spuVO.getBounds();
        SpuBoundsTo spuBoundsTo = new SpuBoundsTo();
        BeanUtils.copyProperties(bounds,spuBoundsTo);
        spuBoundsTo.setSpuId(spuInfoEntity.getId());
        R r = couponFeignService.saveSpuBounds(spuBoundsTo);
        if (r.getCode()!=0){
            log.error("远程保存spu积分信息失败");
        }
        //6.保存spu对应的sku信息
        List<Skus> skus = spuVO.getSkus();
        if (skus != null && skus.size() > 0) {
            skus.forEach((item) -> {
                String defaultImg="";
                for (Images image : item.getImages()) {
                    if (image.getDefaultImg()==1){
                        defaultImg=image.getImgUrl();
                    }
                }

                SkuInfoEntity skuInfoEntity = new SkuInfoEntity();
                BeanUtils.copyProperties(item, skuInfoEntity);
                skuInfoEntity.setBrandId(spuInfoEntity.getBrandId());
                skuInfoEntity.setCatalogId(spuInfoEntity.getCatalogId());
                skuInfoEntity.setSaleCount(0L);
                skuInfoEntity.setSpuId(spuInfoEntity.getId());
                skuInfoEntity.setSkuDefaultImg(defaultImg);

                //6.1、保存sku基本信息 pms_sku_info
                skuInfoService.save(skuInfoEntity);

                Long skuId = skuInfoEntity.getSkuId();
                List<Images> itemImages = item.getImages();
                List<SkuImagesEntity> imagesEntities = itemImages.stream().map(img -> {
                    SkuImagesEntity skuImagesEntity = new SkuImagesEntity();
                    skuImagesEntity.setSkuId(skuId);
                    skuImagesEntity.setImgUrl(img.getImgUrl());
                    skuImagesEntity.setDefaultImg(img.getDefaultImg());
                    return skuImagesEntity;
                }).collect(Collectors.toList());
                //6.2、保存sku图片信息 pms_sku_images
                skuImagesService.saveBatch(imagesEntities);

                //6.3、保存sku销售属性信息      pms_sku_sale_attr_value
                List<Attr> attr = item.getAttr();
                List<SkuSaleAttrValueEntity> skuSaleAttrValueEntities = attr.stream().map(a -> {
                    SkuSaleAttrValueEntity skuSaleAttrValueEntity = new SkuSaleAttrValueEntity();
                    BeanUtils.copyProperties(a, skuSaleAttrValueEntity);
                    skuSaleAttrValueEntity.setSkuId(skuId);
                    return skuSaleAttrValueEntity;
                }).collect(Collectors.toList());
                skuSaleAttrValueService.saveBatch(skuSaleAttrValueEntities);

                //6.4、保存sku优惠、满减等信息 mall_sms->sms_sku_ladder、sms_sku_full_reduction、sms_member_price
                SkuReductionTo skuReductionTo=new SkuReductionTo();
                BeanUtils.copyProperties(item,skuReductionTo);
                skuReductionTo.setSkuId(skuId);
                R r1 = couponFeignService.saveSkuReduction(skuReductionTo);
                if (r1.getCode()!=0){
                    log.error("远程保存sku优惠信息失败");
                }
            });
        }





    }

}