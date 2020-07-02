package com.example.hotel.vo;


import lombok.*;

/**
 * @author 13524
 */
@Data
public class RoomVO {
    private Integer id;
    private String roomType;
    private Double price;
    private Integer curNum;
    private Integer total;

}
