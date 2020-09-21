package com.cmc.mall.ware.entity;

import lombok.Data;

import java.util.List;

/**
 * @author chengmingchao
 * @version 1.0
 * @date 2020/9/21 9:54 下午
 */
@Data
public class MergeVo {
    private Long purchaseId;
    private List<Long> items;
}
