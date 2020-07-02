package com.example.hotel.po;

import com.example.hotel.enums.BizRegion;
import com.example.hotel.enums.HotelStar;
import lombok.*;


@Data
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

}

