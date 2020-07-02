package com.example.hotel.data.hotel;

import com.example.hotel.po.Hotel;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.vo.HotelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hmb
 */
@Mapper
@Repository
public interface HotelMapper {

    /**
     * @param hotel
     * @return
     */
    int insertHotel(Hotel hotel);

    /**
     * @return 列表
     */
    List<HotelVO> selectAllHotel();

    /**
     * @param id
     * @return
     */
    HotelVO selectById(@Param("id") Integer id);

    /**
     * 更新酒店
     * @param hotel PO
     */
    void updateHotel(Hotel hotel);

    /**
     * 选择这个酒店人管理的酒店
     * @param id 酒店人id
     * @return
     */
    List<HotelVO> selectManagerHotel(int id);

    /**
     * 根据下面，更新酒店信息
     * @param hotelId
     * @param name
     * @param address
     * @param bizRegion
     * @param description
     * @param hotelStar
     * @param phoneNum
     * @return
     */
    int updateInfo(@Param("id") Integer hotelId, @Param("name")String name, @Param("address") String address, @Param("bizRegion") String bizRegion,
                   @Param("hotelDescription") String description, @Param("hotelStar")String hotelStar, @Param("phoneNum")String phoneNum);




}
