package com.example.hotel.po;

import com.example.hotel.enums.RoomType;
import lombok.*;

@Data
public class HotelRoom {
    private Integer id;
    private RoomType roomType;
    private Integer hotelId;
    private Double price;
    /**
     * 当前剩余可预定房间数
     */
    private Integer curNum;
    /**
     * 某类型房间总数
     */
    private Integer total;

}
