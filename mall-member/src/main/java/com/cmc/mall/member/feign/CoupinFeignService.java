package com.cmc.mall.member.feign;

import com.cmc.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chengmingchao
 * @version 1.0
 * @date 2020/8/9 4:57 下午
 */
@FeignClient("mall-coupon")
public interface CoupinFeignService {

    @RequestMapping("coupon/coupon/member/list")
    R getMemberList();
}
