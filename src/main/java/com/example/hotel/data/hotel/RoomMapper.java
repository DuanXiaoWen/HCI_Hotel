package com.example.hotel.data.hotel;

import com.example.hotel.po.HotelRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoomMapper {

    int insertRoom(HotelRoom hotelRoom);

    List<HotelRoom> getAllRoomList();

    List<HotelRoom> selectRoomsByHotelId(@Param("hotelId") Integer hotelId);

    int getRoomTotalNum(@Param("hotelId") Integer hotelId, @Param("roomType") String roomType);
}
