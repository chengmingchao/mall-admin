/**
  * Copyright 2020 bejson.com 
  */
package com.cmc.mall.product.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 积分信息
 */
@Data
public class Bounds {

    private BigDecimal buyBounds;
    private BigDecimal growBounds;


}