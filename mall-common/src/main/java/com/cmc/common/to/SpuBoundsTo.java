package com.cmc.common.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author chengmingchao
 * @version 1.0
 * @date 2020/9/15 10:11 下午
 */
@Data
public class SpuBoundsTo {
    private Long spuId;
    private BigDecimal buyBounds;
    private BigDecimal growBounds;
}
