package com.example.hotel.bl.coupon;

import com.example.hotel.po.Coupon;
import com.example.hotel.vo.CouponVO;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;

import java.util.List;

public interface CouponService {
    /**
     * 返回某一订单可用的优惠策略列表
     * @param orderVO
     * @return
     */
    List<Coupon> getMatchOrderCoupon(OrderVO orderVO);

    /**
     * 查看某个酒店提供的所有优惠策略（包括失效的）
     * @param hotelId
     * @return
     */
    List<Coupon> getHotelAllCoupon(Integer hotelId);

    /**
     * 查看所有优惠策略
     * @return
     */
    List<Coupon> getAllCoupon();

    /**
     * 添加优惠策略
     * @param couponVO
     * @return
     */
    CouponVO addCoupon(CouponVO couponVO);

    /**
     * 删除优惠策略
     * @param couponId
     * @return
     */
    ResponseVO deleteCoupon(Integer couponId);
}
