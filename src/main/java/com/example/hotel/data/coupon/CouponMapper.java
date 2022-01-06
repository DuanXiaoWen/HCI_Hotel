package com.example.hotel.data.coupon;

import com.example.hotel.po.Coupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 13524
 */
@Mapper
@Repository
public interface CouponMapper {

    /**
     * 添加优惠
     * @param coupon
     * @return
     */
    int insertCoupon(Coupon coupon);

    /**
     * @param hotelId
     * @return
     */
    List<Coupon> selectByHotelId(Integer hotelId);

    /**
     * @return
     */
    List<Coupon> selectAll();

    /**
     * @param couponId
     * @return
     */
    void deleteCoupon(Integer couponId);
}
