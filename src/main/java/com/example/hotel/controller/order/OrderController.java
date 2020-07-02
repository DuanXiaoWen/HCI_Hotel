package com.example.hotel.controller.order;

import com.example.hotel.bl.order.OrderService;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: chenyizong
 * @Date: 2020-02-29
 */


@RestController()
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrder")
    public ResponseVO reserveHotel(@RequestBody OrderVO orderVO){
        return orderService.addOrder(orderVO);
    }

    @GetMapping("/getAllOrders")
    public ResponseVO retrieveAllOrders(){
        return ResponseVO.buildSuccess(orderService.getAllOrders());
    }

    @GetMapping("/{userId}/getUserOrders")
    public  ResponseVO retrieveUserOrders(@PathVariable int userId){
        return ResponseVO.buildSuccess(orderService.getUserOrders(userId));
    }


    @GetMapping("/{orderid}/annulOrder")
    public ResponseVO annulOrder(@PathVariable int orderid){
        return orderService.annulOrder(orderid);
    }

    @GetMapping("/{hotelId}/allOrders")
    public ResponseVO retrieveHotelOrders(@PathVariable int hotelId) {
        return ResponseVO.buildSuccess(orderService.getHotelOrders(hotelId));
    }

    @PostMapping("addComment")
    public ResponseVO addComment(@RequestBody OrderVO orderVO){
        return orderService.addComment(orderVO);
    }

    @GetMapping("/{orderId}/getCommentByOrderId")
    public ResponseVO getCommentByOrderId(@PathVariable int orderId){
        try {
            return orderService.getCommentByOrderId(orderId);
        }catch (Exception e){
            return ResponseVO.buildFailure("fail to fetch the order");
        }

    }

    @GetMapping("/{hotelId}/getHotelComment")
    public ResponseVO getHotelComment(@PathVariable Integer hotelId){
        return ResponseVO.buildSuccess(orderService.getHotelComment(hotelId));
    }

    @GetMapping("/{id}/checkIn")
    public ResponseVO checkIn(@PathVariable int id){return  orderService.checkIn(id);}

    @GetMapping("/{id}/checkOut")
    public ResponseVO checkOut(@PathVariable int id){return  orderService.checkOut(id);}

    @GetMapping("/abnormalOrdersOfTheDay")
    public ResponseVO getHotelAbOrders(@RequestParam Integer hotelId) {
        //System.out.println("用户："+orderService.getHotelAbOrders(hotelId).get(0).getCheckOutDate());
        return ResponseVO.buildSuccess(orderService.getHotelAbOrders(hotelId));
    }


}
