package com.example.hotel.vo;


import lombok.*;

import java.util.List;

/**
 * @author 13524
 */


@Data
public class HotelVO {
    private Integer id;
    private String name;
    private String address;
    private String bizRegion;
    private String hotelStar;
    private Double rate;
    private String description;
    private String phoneNum;
    private Integer hotelState;
    private Integer managerId;
    private Integer commentNumber;
    private Integer totalCommentScore;

    private List<RoomVO> rooms;

}
