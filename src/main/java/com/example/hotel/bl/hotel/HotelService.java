package com.example.hotel.bl.hotel;

import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.ResponseVO;
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


    /**
     * @param hotelId id
     * @param score 分数
     *  添加评论
     */
    void addComment(Integer hotelId, Integer score);
    /**
     * 查看酒店管理人员的所有酒店
     * @param id 不解释
     * @return 列表
     */
    List<HotelVO> retrieveManagerHotels(int id);

    /**
     * @param hotelId
     * @param name
     * @param address
     * @param bizRegion
     * @param description
     * @param hotelStar
     * @param phoneNum
     * @return 更新以上酒店信息入库
     */
    ResponseVO updateHotelInfo(Integer hotelId, String name, String address, String bizRegion, String description, String hotelStar, String phoneNum);

    /**
     * @param hotelId 酒店id
     * @param email 被转让人的邮件
     * @return 申请酒店转让
     */
    ResponseVO giveUpHotel(Integer hotelId, String email);

    /**
     * @param hotelId id
     * @param accept true表示接受转让，false表示拒绝
     * @return 接受或拒绝酒店
     */
    ResponseVO acceptOrRefuseHotel(Integer hotelId, boolean accept);
}
