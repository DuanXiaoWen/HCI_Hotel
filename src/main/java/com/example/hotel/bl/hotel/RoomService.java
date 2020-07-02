package com.example.hotel.bl.hotel;

import com.example.hotel.po.HotelRoom;

import java.util.List;

public interface RoomService {

    /**
     * 列表获取所有房间信息
     * @param
     * @return
     */
    List<HotelRoom> getAllRoomList();

    /**
     * 获取某个酒店的全部房间信息
     * @param hotelId
     * @return
     */
    List<HotelRoom> retrieveHotelRoomInfo(Integer hotelId);

    /**
     * 添加酒店客房信息
     * @param hotelRoom
     */
    void insertRoomInfo(HotelRoom hotelRoom);


    /**
     * 获取酒店某种房间的总数量
     * @param hotelId
     * @param roomType
     * @return
     */
    int getRoomTotalNum(Integer hotelId, String roomType);
}
