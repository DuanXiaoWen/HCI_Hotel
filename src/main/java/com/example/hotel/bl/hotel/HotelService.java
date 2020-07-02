package com.example.hotel.bl.hotel;

import com.example.hotel.po.HotelRoom;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.RoomVO;
import com.example.hotel.vo.HotelVO;

import java.util.List;

public interface HotelService {

    /**
     * 添加酒店
     * @param hotelVO
     * @throws
     */
    void addHotel(HotelVO hotelVO) throws ServiceException;


    /**
     * 列表获取酒店信息
     * @return
     */
    List<HotelVO> retrieveHotels();

    /**
     * 获取某家酒店详细信息
     * @param hotelId
     * @return
     */
    HotelVO retrieveHotelDetails(Integer hotelId);

    /**
     * 查看酒店某种房间的总数量
     * @param hotelId
     * @param roomType
     * @return
     */
    int getRoomTotalNum(Integer hotelId, String roomType);


    void addComment(Integer hotelId, Integer score);
    /**
     * 查看酒店管理人员的所有酒店
     * @param id
     * @return
     */
    List<HotelVO> retrieveMgrHotels(int id);

    ResponseVO updateHotelInfo(Integer hotelId, String name, String address, String bizRegion, String description, String hotelStar, String phoneNum);

    ResponseVO giveUpHotel(Integer hotelId, String email);

    ResponseVO acceptOrRefuseHotel(Integer hotelId, boolean accept);
}
