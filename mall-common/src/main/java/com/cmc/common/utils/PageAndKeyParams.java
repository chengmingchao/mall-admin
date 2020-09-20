package com.cmc.common.utils;

import lombok.Data;

/**
 * @author chengmingchao
 * @version 1.0
 * @date 2020/9/3 9:54 下午
 */
@Data
public class PageAndKeyParams {
    private Integer pageNum;
    private Integer pageSize;
    private String key;
}
