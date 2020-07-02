package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.po.Coupon;
import com.example.hotel.util.CouponMatchStrategyService;
import com.example.hotel.vo.OrderVO;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

@CouponMatchStrategyService(CouponMatchStrategyService.TimeCouponStrategy)
public class TimeCouponStrategyImpl implements CouponMatchStrategy {
    /**
     * 判断某个订单是否满足某种限时优惠策略
     * @param orderVO
     * @param coupon
     * @return
     */
    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon) {

        String orderDate = orderVO.getCreateDate(); //"yyyy-mm-dd"
        LocalDateTime orderTime = LocalDateTime.of(
                Integer.valueOf(orderDate.substring(0, 4)),
                Integer.valueOf(orderDate.substring(5, 7)),
                Integer.valueOf(orderDate.substring(8, 10)),
                0, 0, 0, 0);

        return orderTime.compareTo(coupon.getStartTime()) >= 0 &&
                orderTime.compareTo(coupon.getEndTime()) <= 0;
    }

    @Test
    public void testTimeCoupon01(){
        OrderVO orderVO = new OrderVO();
        Coupon coupon = new Coupon();
        orderVO.setCreateDate("2020-06-27");
        coupon.setStartTime(LocalDateTime.of(2020,6,28,0, 0, 0, 0));
        coupon.setEndTime(LocalDateTime.of(2020,7,3,0, 0, 0, 0));
        Assert.assertFalse(this.isMatch(orderVO,coupon));
    }
    @Test
    public void testTimeCoupon02(){
        OrderVO orderVO = new OrderVO();
        Coupon coupon = new Coupon();
        orderVO.setCreateDate("2020-06-28");
        coupon.setStartTime(LocalDateTime.of(2020,6,28,0, 0, 0, 0));
        coupon.setEndTime(LocalDateTime.of(2020,7,3,0, 0, 0, 0));
        Assert.assertTrue(this.isMatch(orderVO,coupon));
    }
    @Test
    public void testTimeCoupon03(){
        OrderVO orderVO = new OrderVO();
        Coupon coupon = new Coupon();
        orderVO.setCreateDate("2020-07-03");
        coupon.setStartTime(LocalDateTime.of(2020,6,28,0, 0, 0, 0));
        coupon.setEndTime(LocalDateTime.of(2020,7,3,0, 0, 0, 0));
        Assert.assertTrue(this.isMatch(orderVO,coupon));
    }
}
