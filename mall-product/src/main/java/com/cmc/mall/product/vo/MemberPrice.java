/**
  * Copyright 2020 bejson.com 
  */
package com.cmc.mall.product.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MemberPrice {

    private Long id;
    private String name;
    private BigDecimal price;


}