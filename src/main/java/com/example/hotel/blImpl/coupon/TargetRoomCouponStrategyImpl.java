package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.po.Coupon;
import com.example.hotel.util.CouponMatchStrategyService;
import com.example.hotel.vo.OrderVO;

@CouponMatchStrategyService(CouponMatchStrategyService.TargetRoomCouponStrategy)
public class TargetRoomCouponStrategyImpl implements CouponMatchStrategy {


    /**
     * 判断某个订单是否满足某种多间优惠策略
     * @param orderVO
     * @param coupon
     * @return
     */
    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon) {

        return orderVO.getRoomNum() >= coupon.getTargetRoom();

    }
}
