package com.example.hotel.po;

import com.example.hotel.enums.BizRegion;
import com.example.hotel.enums.HotelStar;

public class Hotel {
    private Integer id;
    private String hotelName;
    private String address;
    /**
     * 商圈
     */
    private BizRegion bizRegion;
    private HotelStar hotelStar;
    private Double rate;
    private String description;
    private String phoneNum;

    private Integer hotelState;
    /**
     * 酒店管理员id
     */
    private Integer managerId;

    private Integer commentNumber;
    private Integer totalCommentScore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BizRegion getBizRegion() {
        return bizRegion;
    }

    public void setBizRegion(BizRegion bizRegion) {
        this.bizRegion = bizRegion;
    }

    public HotelStar getHotelStar() {
        return hotelStar;
    }

    public void setHotelStar(HotelStar hotelStar) {
        this.hotelStar = hotelStar;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Integer commentNumber) {
        this.commentNumber = commentNumber;
    }

    public Integer getTotalCommentScore() {
        return totalCommentScore;
    }

    public void setTotalCommentScore(Integer totalCommentScore) {
        this.totalCommentScore = totalCommentScore;
    }

    public Integer getHotelState() {
        return hotelState;
    }

    public void setHotelState(Integer hotelState) {
        this.hotelState = hotelState;
    }
}

