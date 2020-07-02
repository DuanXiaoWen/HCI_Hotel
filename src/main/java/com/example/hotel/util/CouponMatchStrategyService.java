package com.example.hotel.util;

import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Service
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})

public @interface CouponMatchStrategyService {

    int
            BirthdayCouponStrategy=1,
            TargetRoomCouponStrategy=2,
            TargetMoneyCouponStrategy=3,
            TimeCouponStrategy = 4;
    int value();
}
