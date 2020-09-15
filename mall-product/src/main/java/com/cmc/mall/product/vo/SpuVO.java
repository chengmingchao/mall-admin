/**
  * Copyright 2020 bejson.com 
  */
package com.cmc.mall.product.vo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SpuVO {

    private String spuName;
    private String spuDescription;
    private Long catalogId;
    private Long brandId;
    private BigDecimal weight;
    private int publishStatus;
    private List<String> decript;
    private List<String> images;
    private Bounds bounds;
    private List<BaseAttrs> baseAttrs;
    private List<Skus> skus;

}