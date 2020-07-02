package com.example.hotel.data.order;

import com.example.hotel.po.Order;
import com.example.hotel.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@Mapper
@Repository
public interface OrderMapper {

    /**
     * @param order
     * @return
     */
    int addOrder(Order order);

    /**
     * @return
     */
    List<Order> getAllOrders();

    /**
     * @param userid
     * @return 该用户所有订单
     */
    List<Order> getUserOrders(@Param("userid") int userid);

    /**
     * @param hotelId
     * @return 该酒店所有订单
     */
    List<Order> getHotelOrders(@Param("hotelId") int hotelId);

    /**
     * @param hotelId
     * @return 该酒店所有异常订单
     */
    List<Order> getHotelAbnormalOrders(@Param("hotelId") Integer hotelId);


    /**
     * @param orderid
     * @param orderState 新的房间状态，异常、已执行等
     * @return
     */
    int updateOrderState(@Param("orderid") int orderid, @Param("orderState") String orderState);

    /**
     * 按ID获取订单
     * @param orderid
     * @return
     */
    Order getOrderById(@Param("orderid") int orderid);


    /**
     * 更新实际退房时间，主要是为了提高效率，一些人提前退房，就已经可以投入使用了
     * @param id
     * @param outdate
     * @return
     */
    int updateOutTime(@Param("id") int id,@Param("checkOutDate") String outdate);

    /**
     * 插入评论信息
     * @param orderid
     * @param comment
     * @param commentScore
     */
    void addComment(@Param("orderid")int orderid,@Param("comment")String comment,@Param("commentScore")int commentScore);
}
