package com.example.hotel.po;
import java.time.LocalDateTime;


public class Coupon {
    /**
     * 优惠券id
     */
    private int id;
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
    private double targetMoney;

    /**
     * 折扣
     */
    private double discount;//如果采用折扣，这个一定是正数；否则我是0
    /**
     * 优惠券优惠金额==立减
     */
    private double discountMoney;//上面那个是0的时候用我
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public double getTargetMoney() {
        return targetMoney;
    }

    public double getTargetRoom(){
        return getTargetMoney();
    }

    public void setTargetMoney(double targetMoney) {
        this.targetMoney = targetMoney;
    }

    public double getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(double discountMoney) {
        this.discountMoney = discountMoney;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getCouponType() {
        return couponType;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Coupon() {
    }
}
