package com.example.hotel.po;
import lombok.*;

import java.time.LocalDateTime;


@Data
public class Coupon {
    /**
     * 优惠券id
     */
    private Integer id;
    /**
     * 优惠券描述
     */
    private String description;

    /**
     * 如果为-1 代表是网站推出的优惠
     */
    private Integer hotelId;

    /**
     * 优惠券类型 1生日特惠 2多间特惠 3满减优惠 4限时优惠 ？
     * 规则好多，前后端很难协调哎
     */
    private Integer couponType;
    /**
     * 优惠券名称
     */
    private String couponName;
    /**
     * 优惠券使用门槛
     */
    private Double targetMoney;

    /**
     * 折扣
     * 如果采用折扣，这个一定是正数；否则我是0
     */
    private Double discount;
    /**
     * 优惠券优惠金额==立减
     * 上面那个是0的时候用我
     */
    private Double discountMoney;
    /**
     * 可用时间
     */
    private LocalDateTime startTime;
    /**
     * 失效时间
     */
    private LocalDateTime endTime;

    /**
     * 优惠券状态 是否已经失效 1可用 0失效
     */
    private Integer status;

    public Double getTargetRoom(){
        return getTargetMoney();
    }

}
