package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.po.Coupon;
import com.example.hotel.util.CouponMatchStrategyService;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.po.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@CouponMatchStrategyService(CouponMatchStrategyService.BirthdayCouponStrategy)
public class BirthdayCouponStrategyImpl implements CouponMatchStrategy {

    @Autowired
    AccountService accountService;

    /**
     * 判断某个订单是否满足某种生日优惠策略
     * @param orderVO,coupon
     * @return
     */

    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon) {
        User user = accountService.getUserInfo(orderVO.getUserId());

        String orderDate = orderVO.getCreateDate(); //"yyyy-mm-dd"
        String birthday = user.getBirthday();
        LocalDateTime orderTime = LocalDateTime.of(
                Integer.valueOf(orderDate.substring(0, 4)),
                Integer.valueOf(orderDate.substring(5, 7)),
                Integer.valueOf(orderDate.substring(8, 10)),
                0, 0, 0, 0);
        LocalDateTime birthDate = LocalDateTime.of(
                Integer.valueOf(orderDate.substring(0, 4)),
                Integer.valueOf(birthday.substring(5, 7)),
                Integer.valueOf(birthday.substring(8, 10)),
                0, 0, 0, 0);

        return orderTime.equals(birthDate);
    }
}
