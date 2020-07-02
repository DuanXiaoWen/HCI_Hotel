package com.example.hotel.vo;

import java.time.LocalDateTime;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getHotelId() { return hotelId; }

    public void setHotelId() { this.hotelId = hotelId; }

    public Double getTarget() { return target; }

    public void setTarget(Double target) { this.target = target; }

    public Double getDiscount() { return discount; }

    public void setDiscount(Double discount) { this.discount = discount; }

    public Double getDiscountMoney() { return discountMoney; }

    public void setDiscountMoney(Double discountMoney) { this.discountMoney = discountMoney; }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
}
