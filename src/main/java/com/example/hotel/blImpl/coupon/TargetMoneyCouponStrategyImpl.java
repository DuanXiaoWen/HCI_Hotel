package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.po.Coupon;
import com.example.hotel.util.CouponMatchStrategyService;
import com.example.hotel.vo.OrderVO;
import org.junit.Assert;
import org.junit.Test;

@CouponMatchStrategyService(CouponMatchStrategyService.TARGET_MONEY_COUPON_STRATEGY)
public class TargetMoneyCouponStrategyImpl implements CouponMatchStrategy {


    /**
     * 判断某个订单是否满足某种满减金额优惠策略
     * @param orderVO
     * @param coupon
     * @return
     */
    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon) {

        return orderVO.getPrice() >= coupon.getTargetMoney();

    }

    @Test
    public void testTargetMoney01(){
        OrderVO orderVO = new OrderVO();
        Coupon coupon = new Coupon();
        orderVO.setPrice(6.66);
        coupon.setTargetMoney(6.67);
        Assert.assertFalse(this.isMatch(orderVO,coupon));
    }

    @Test
    public void testTargetMoney02(){
        OrderVO orderVO = new OrderVO();
        Coupon coupon = new Coupon();
        orderVO.setPrice(6.67);
        coupon.setTargetMoney(6.67);
        Assert.assertTrue(this.isMatch(orderVO,coupon));
    }

    @Test
    public void testTargetMoney03(){
        OrderVO orderVO = new OrderVO();
        Coupon coupon = new Coupon();
        orderVO.setPrice(6.67);
        coupon.setTargetMoney(6.66);
        Assert.assertTrue(this.isMatch(orderVO,coupon));
    }
}
