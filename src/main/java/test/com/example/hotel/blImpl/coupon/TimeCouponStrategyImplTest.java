package test.com.example.hotel.blImpl.coupon; 

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.blImpl.coupon.TimeCouponStrategyImpl;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.OrderVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.time.LocalDateTime;

/** 
* TimeCouponStrategyImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 2, 2020</pre> 
* @version 1.0 
*/ 
public class TimeCouponStrategyImplTest { 
private CouponMatchStrategy timeCouponStrategy = new TimeCouponStrategyImpl();
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
    public void testTimeCoupon01(){
        OrderVO orderVO = new OrderVO();
        Coupon coupon = new Coupon();
        orderVO.setCreateDate("2020-06-27");
        coupon.setStartTime(LocalDateTime.of(2020,6,28,0, 0, 0, 0));
        coupon.setEndTime(LocalDateTime.of(2020,7,3,0, 0, 0, 0));
        Assert.assertFalse(timeCouponStrategy.isMatch(orderVO,coupon));
    }
    @Test
    public void testTimeCoupon02(){
        OrderVO orderVO = new OrderVO();
        Coupon coupon = new Coupon();
        orderVO.setCreateDate("2020-06-28");
        coupon.setStartTime(LocalDateTime.of(2020,6,28,0, 0, 0, 0));
        coupon.setEndTime(LocalDateTime.of(2020,7,3,0, 0, 0, 0));
        Assert.assertTrue(timeCouponStrategy.isMatch(orderVO,coupon));
    }
    @Test
    public void testTimeCoupon03(){
        OrderVO orderVO = new OrderVO();
        Coupon coupon = new Coupon();
        orderVO.setCreateDate("2020-07-03");
        coupon.setStartTime(LocalDateTime.of(2020,6,28,0, 0, 0, 0));
        coupon.setEndTime(LocalDateTime.of(2020,7,3,0, 0, 0, 1));
        Assert.assertTrue(timeCouponStrategy.isMatch(orderVO,coupon));
    }

} 
