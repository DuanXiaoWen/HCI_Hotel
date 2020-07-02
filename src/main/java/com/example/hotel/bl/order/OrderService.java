package com.example.hotel.bl.order;

import com.example.hotel.po.Order;
import com.example.hotel.vo.CommentVO;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
public interface OrderService {

    /**
     * 预订酒店
     * @param orderVO
     * @return
     */
    ResponseVO addOrder(OrderVO orderVO);

    /**
     * 获得所有订单信息
     * @return
     */
    List<Order> getAllOrders();

    /**
     * 获得指定用户的所有订单信息
     * @param userid
     * @return
     */
    List<Order> getUserOrders(int userid);
    /**
     * 获得指定宾馆所有订单
     * @hotelId
     * @return
     */
    List<Order> getHotelOrders(int hotelId);

    /**
     * 撤销订单
     * @param orderId
     * @return
     */
    ResponseVO annulOrder(int orderId);

    /**
     * 退房he入住
     * @param orderId
     * @return
     */
    ResponseVO checkIn(int orderId);
    ResponseVO checkOut(int orderId);
    /**
     * 异常订单
     * @param hotelId
     * @return
     */

    List<Order> getHotelAbnormalOrders(Integer hotelId);


    /**
     * 添加订单
     * @param orderVO 订单信息
     * @return
     */
    ResponseVO addComment(OrderVO orderVO);



    /**
     * 获取酒店评论，根据
     * @param orderId 订单id
     * @return 酒店评论
     */
    ResponseVO getCommentByOrderId(Integer orderId);

    /**
     * 列表获取某酒店评论
     * @param hotelId
     * @return 列表
     */
    List<CommentVO> getHotelComment(Integer hotelId);


}
