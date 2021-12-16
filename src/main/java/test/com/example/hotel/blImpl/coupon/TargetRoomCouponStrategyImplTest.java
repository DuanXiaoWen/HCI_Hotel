package test.com.example.hotel.blImpl.coupon; 

import com.example.hotel.blimpl.coupon.TargetRoomCouponStrategyImpl;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.OrderVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* TargetRoomCouponStrategyImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 2, 2020</pre> 
* @version 1.0 
*/ 
public class TargetRoomCouponStrategyImplTest { 
private TargetRoomCouponStrategyImpl targetRoomCouponStrategy = new TargetRoomCouponStrategyImpl();
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
    public void testIsMatch01() throws Exception {
        OrderVO orderVO = new OrderVO();
        Coupon coupon = new Coupon();
        orderVO.setRoomNum(16);
        coupon.setTargetMoney(16.0);
        Assert.assertTrue(targetRoomCouponStrategy.isMatch(orderVO,coupon));
    }
    @Test
    public void testIsMatch02() throws Exception {
        OrderVO orderVO = new OrderVO();
        Coupon coupon = new Coupon();
        orderVO.setRoomNum(16);
        coupon.setTargetMoney(17.0);
        Assert.assertFalse(targetRoomCouponStrategy.isMatch(orderVO,coupon));
    }
    @Test
    public void testIsMatch03() throws Exception {
        OrderVO orderVO = new OrderVO();
        Coupon coupon = new Coupon();
        orderVO.setRoomNum(17);
        coupon.setTargetMoney(16.0);
        Assert.assertTrue(targetRoomCouponStrategy.isMatch(orderVO,coupon));
    }

} 
