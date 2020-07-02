package com.example.hotel.bl.coupon;

import com.example.hotel.po.Coupon;
import com.example.hotel.vo.OrderVO;

/**
 * @author 黄孟斌
 * 2020/05/14
 */
@FunctionalInterface
public interface CouponMatchStrategy {
    /**
     * @param orderVO 订单
     * @param coupon 优惠券
     * @return 是否匹配
     */
    boolean isMatch(OrderVO orderVO, Coupon coupon);
}
