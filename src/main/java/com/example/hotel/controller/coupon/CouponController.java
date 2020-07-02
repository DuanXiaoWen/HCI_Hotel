package com.example.hotel.controller.coupon;

import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.vo.CouponVO;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping("/addCoupon")
    public ResponseVO addCoupon(@RequestBody CouponVO couponVO) {
        try {   //虽然这里有try catch, 但如果 HotelTargetMoneyCouponVO 格式不合法(比如价格无法转换成int), 将不会进入这里
            CouponVO couponVO1 = couponService.addCoupon(couponVO);
            return ResponseVO.buildSuccess(couponVO1);
        }
        catch (Exception e) {
            return ResponseVO.buildFailure(e.getMessage());
        }
    }

    @GetMapping("/hotelAllCoupons")
    public ResponseVO getHotelAllCoupons(@RequestParam Integer hotelId) {
        return ResponseVO.buildSuccess(couponService.getHotelAllCoupon(hotelId));
    }

    @GetMapping("/orderMatchCoupons")
    public ResponseVO getOrderMatchCoupons(@RequestParam Integer userId,
                                           @RequestParam Integer hotelId,
                                           @RequestParam Double orderPrice,
                                           @RequestParam Integer roomNum,
                                           @RequestParam String checkIn,
                                           @RequestParam String checkOut) {
        OrderVO requestOrderVO = new OrderVO();
        requestOrderVO.setUserId(userId);
        requestOrderVO.setHotelId(hotelId);
        requestOrderVO.setPrice(orderPrice);
        requestOrderVO.setRoomNum(roomNum);
        requestOrderVO.setCheckInDate(checkIn);
        requestOrderVO.setCheckOutDate(checkOut);
        return ResponseVO.buildSuccess(couponService.getMatchOrderCoupon(requestOrderVO));
    }


}
