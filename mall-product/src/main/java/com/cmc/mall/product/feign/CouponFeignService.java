package com.cmc.mall.product.feign;

import com.cmc.common.to.SkuReductionTo;
import com.cmc.common.to.SpuBoundsTo;
import com.cmc.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chengmingchao
 * @version 1.0
 * @date 2020/9/15 10:04 下午
 */
@FeignClient("mall-coupon")
public interface CouponFeignService {

    @PostMapping("/coupon/spubounds/save")
    R saveSpuBounds(@RequestBody SpuBoundsTo spuBoundsTo);

    @PostMapping("coupon/skufullreduction/saveInfo")
    R saveSkuReduction(@RequestBody SkuReductionTo skuReductionTo);
}
