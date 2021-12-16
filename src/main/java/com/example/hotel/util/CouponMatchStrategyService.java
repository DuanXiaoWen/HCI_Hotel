package com.example.hotel.util;

import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 黄孟斌
 * 这是一个注解，打到优惠券匹配类的前面，value填这种优惠的标志值
 */
@Service
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})

public @interface CouponMatchStrategyService {

    int
            BIRTHDAY_COUPON_STRATEGY = 1,
            TARGET_ROOM_COUPON_STRATEGY = 2,
            TARGET_MONEY_COUPON_STRATEGY = 3,
            TIME_COUPON_STRATEGY = 4;
    int value();
}
