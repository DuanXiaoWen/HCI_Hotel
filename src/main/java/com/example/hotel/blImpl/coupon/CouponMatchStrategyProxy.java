package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.po.Coupon;
import com.example.hotel.util.CouponMatchStrategyService;
import com.example.hotel.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CouponMatchStrategyProxy implements CouponMatchStrategy {

    private final Map<Integer,CouponMatchStrategy> strategyMap = new HashMap<>();

    @Autowired
    public CouponMatchStrategyProxy(ApplicationContext applicationContext) {
        System.out.println("\033[35;0m优惠策略信息如下\033[0m:");
        applicationContext.getBeansWithAnnotation(CouponMatchStrategyService.class).values().stream().map(o->(CouponMatchStrategy)o).forEach(couponMatchStrategy -> {
            final int key = couponMatchStrategy.getClass().getAnnotation(CouponMatchStrategyService.class).value();
            if (strategyMap.get(key) != null) {
                throw new RuntimeException("\033[31;0m怎么出现了两个相同的优惠策略值？\n\033[0m" +
                        "\033[35;0m\tkey=" + key + "\t" +
                        "class=" + strategyMap.get(key).getClass().getSimpleName() + ", " + couponMatchStrategy.getClass().getSimpleName() + "\033[0m");
            } else {
                strategyMap.put(key, couponMatchStrategy);
            }
            System.out.println("\t\033[33;0m" + key + "\t\033[30;0m -> \t\033[36;0m" + couponMatchStrategy.getClass().getSimpleName() + "\033[0m");
        });
    }

    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon) {
        return this.strategyMap.get(coupon.getCouponType()).isMatch(orderVO, coupon);
    }
}
