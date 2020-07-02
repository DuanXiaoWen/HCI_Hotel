package com.example.hotel.blImpl.coupon;
import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.data.coupon.CouponMapper;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CouponServiceImpl implements CouponService {



    private final CouponMapper couponMapper;

    private final CouponMatchStrategy couponMatchStrategyProxy;

    @Autowired
    public CouponServiceImpl(CouponMapper couponMapper, CouponMatchStrategyProxy couponMatchStrategyProxy) {
        this.couponMapper = couponMapper;
        this.couponMatchStrategyProxy = couponMatchStrategyProxy;
    }



    @Override
    public List<Coupon> getMatchOrderCoupon(OrderVO orderVO) {

        List<Coupon> hotelCoupons = getHotelAllCoupon(orderVO.getHotelId());
        hotelCoupons.addAll(getWebAllCoupon());

        List<Coupon> availAbleCoupons = new ArrayList<>();

        for (Coupon hotelCoupon : hotelCoupons) {
            if (couponMatchStrategyProxy.isMatch(orderVO,hotelCoupon)) {
                availAbleCoupons.add(hotelCoupon);
            }
        }
        return availAbleCoupons;
    }

    @Override
    public List<Coupon> getHotelAllCoupon(Integer hotelId) {
        return couponMapper.selectByHotelId(hotelId);
    }

    private List<Coupon> getWebAllCoupon(){
        return couponMapper.selectByHotelId(-1);
    }

    @Override
    public CouponVO addCoupon(CouponVO couponVO) {
        Coupon coupon = new Coupon();
        coupon.setCouponName(couponVO.getName());
        coupon.setDescription(couponVO.getDescription());
        coupon.setCouponType(couponVO.getType());
        coupon.setHotelId(couponVO.getHotelId());
        if(couponVO.getTarget()!=null)
            coupon.setTargetMoney(couponVO.getTarget());
        coupon.setDiscount(couponVO.getDiscount());
        coupon.setDiscountMoney(couponVO.getDiscountMoney());
        if(couponVO.getStartTime()!=null)
            coupon.setStartTime(couponVO.getStartTime());
        if(couponVO.getEndTime()!=null)
            coupon.setEndTime(couponVO.getEndTime());
        coupon.setStatus(1);///???

        int result = couponMapper.insertCoupon(coupon);
        couponVO.setId(result);
        return couponVO;
    }

//
//    @Override
//    public CouponVO addHotelTargetMoneyCoupon(HotelTargetMoneyCouponVO hotelTargetMoneyCouponVO) {
//        if(hotelTargetMoneyCouponVO.getTargetMoney() <= hotelTargetMoneyCouponVO.getDiscountMoney()){
//            throw new RuntimeException("你在想peach");
//        }
//        Coupon coupon = new Coupon();
//        coupon.setCouponName(hotelTargetMoneyCouponVO.getName());
//        coupon.setDescription(hotelTargetMoneyCouponVO.getDescription());
//        coupon.setCouponType(hotelTargetMoneyCouponVO.getType());
//        coupon.setTargetMoney(hotelTargetMoneyCouponVO.getTargetMoney());
//        coupon.setHotelId(hotelTargetMoneyCouponVO.getHotelId());
//
//        coupon.setDiscountMoney(hotelTargetMoneyCouponVO.getDiscountMoney());
//
//        coupon.setStatus(1);///？？？
//        int result = couponMapper.insertCoupon(coupon);
//        hotelTargetMoneyCouponVO.setId(result);
//        return hotelTargetMoneyCouponVO;
//    }
//
//    @Override
//    public CouponVO addTimeCoupon(TimeCouponVO timeCouponVO) {
//        Coupon coupon = new Coupon();
//        coupon.setCouponName(timeCouponVO.getName());
//        coupon.setDescription(timeCouponVO.getDescription());
//        coupon.setCouponType(timeCouponVO.getType());
//        coupon.setTargetMoney(timeCouponVO.getTargetMoney());
//        coupon.setHotelId(-1);//默认由平台发起
//        coupon.setDiscountMoney(timeCouponVO.getDiscount());
//        coupon.setStatus(1);///？？？
//        int result = couponMapper.insertCoupon(coupon);
//        timeCouponVO.setId(result);
//        return timeCouponVO;
//    }
//
//    @Override
//    public CouponVO addHotelTargetRoomCoupon(HotelTargetRoomCouponVO hotelTargetRoomCouponVO) {
//        Coupon coupon = new Coupon();
//        coupon.setCouponName(hotelTargetRoomCouponVO.getName());
//        coupon.setDescription(hotelTargetRoomCouponVO.getDescription());
//        coupon.setCouponType(hotelTargetRoomCouponVO.getType());
//        coupon.setTargetMoney(hotelTargetRoomCouponVO.getTargetRoom());
//        coupon.setHotelId(hotelTargetRoomCouponVO.getHotelId());
//        coupon.setDiscountMoney(hotelTargetRoomCouponVO.getDiscountMoney());
//        coupon.setStatus(1);///？？？
//        int result = couponMapper.insertCoupon(coupon);
//        hotelTargetRoomCouponVO.setId(result);
//        return hotelTargetRoomCouponVO;
//    }
//


}
