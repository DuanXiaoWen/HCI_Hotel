package com.example.hotel.data.hotel;

import com.example.hotel.po.HotelRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 13524
 */
@Mapper
@Repository
public interface RoomMapper {

    /**
     * @param hotelRoom
     * @return
     */
    int insertRoom(HotelRoom hotelRoom);

    /**
     * @return
     */
    List<HotelRoom> getAllRoomList();

    /**
     * @param hotelId
     * @return
     */
    List<HotelRoom> selectRoomsByHotelId(@Param("hotelId") Integer hotelId);

    /**
     * 根据酒店、房型选出房间，获取它的总数量
     * @param hotelId
     * @param roomType
     * @return 总数量
     */
    int getRoomTotalNum(@Param("hotelId") Integer hotelId, @Param("roomType") String roomType);
}
