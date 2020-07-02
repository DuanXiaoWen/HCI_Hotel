package test.com.example.hotel.blImpl.coupon; 

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.blImpl.coupon.TargetMoneyCouponStrategyImpl;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.OrderVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* TargetMoneyCouponStrategyImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>6/13, 2020</pre>
* @version 1.0 
*/ 
public class TargetMoneyCouponStrategyImplTest { 

    private CouponMatchStrategy targetMoneyCouponStrategy = new TargetMoneyCouponStrategyImpl();
    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
    *
    * Method: isMatch(OrderVO orderVO, Coupon coupon)
    *
    */
    @Test
    public void testTargetMoney01(){
        OrderVO orderVO = new OrderVO();
        Coupon coupon = new Coupon();
        orderVO.setPrice(6.66);
        coupon.setTargetMoney(6.67);
        Assert.assertFalse(targetMoneyCouponStrategy.isMatch(orderVO,coupon));
    }

    @Test
    public void testTargetMoney02(){
        OrderVO orderVO = new OrderVO();
        Coupon coupon = new Coupon();
        orderVO.setPrice(6.67);
        coupon.setTargetMoney(6.67);
        Assert.assertTrue(targetMoneyCouponStrategy.isMatch(orderVO,coupon));
    }

    @Test
    public void testTargetMoney03(){
        OrderVO orderVO = new OrderVO();
        Coupon coupon = new Coupon();
        orderVO.setPrice(6.67);
        coupon.setTargetMoney(6.66);
        Assert.assertTrue(targetMoneyCouponStrategy.isMatch(orderVO,coupon));
    }




} 
