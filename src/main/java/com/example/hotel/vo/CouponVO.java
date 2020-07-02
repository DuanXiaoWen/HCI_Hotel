package com.example.hotel.vo;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @author 13524
 */
@Data
public class CouponVO {

    private Integer id;
    private String description;
    private Integer status;
    private String name;
    private Integer type;
    private Integer hotelId;

    private Double target;
    private Double discount;
    private Double discountMoney;
    private LocalDateTime startTime;
    private LocalDateTime endTime;


}
