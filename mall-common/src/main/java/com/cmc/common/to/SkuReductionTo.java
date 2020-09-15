package com.cmc.common.to;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author chengmingchao
 * @version 1.0
 * @date 2020/9/15 10:24 下午
 */
@Data
public class SkuReductionTo {
    private Long skuId;
    private int fullCount;
    private BigDecimal discount;
    private int countStatus;
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private BigDecimal priceStatus;
    private List<MemberPrice> memberPrice;
}
