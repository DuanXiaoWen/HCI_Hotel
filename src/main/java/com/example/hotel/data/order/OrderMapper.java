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

    int addOrder(Order order);

    List<Order> getAllOrders();

    List<Order> getUserOrders(@Param("userid") int userid);

    List<Order> getHotelOrders(@Param("hotelId") int hotelId);

    List<Order> getHotelAbOrders(@Param("hotelId") Integer hotelId);


    int updateOrderState(@Param("orderid") int orderid, @Param("orderState") String orderState);

    Order getOrderById(@Param("orderid") int orderid);


    int updateOutTime(@Param("id") int id,@Param("checkOutDate") String outdate);

    void addComment(@Param("orderid")int orderid,@Param("comment")String comment,@Param("commentScore")int commentScore);
}
