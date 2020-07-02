package test.com.example.hotel.blImpl.coupon; 

import com.example.hotel.blImpl.coupon.BirthdayCouponStrategyImpl;
import com.example.hotel.blImpl.user.AccountServiceImpl;
import com.example.hotel.po.Coupon;
import com.example.hotel.po.User;
import com.example.hotel.vo.OrderVO;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.lang.reflect.Field;
import java.time.DateTimeException;

/** 
* BirthdayCouponStrategyImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>7, 2, 2020</pre>
* @version 1.0 
*/ 
public class BirthdayCouponStrategyImplTest {

    private BirthdayCouponStrategyImpl birthdayCouponStrategyImpl = new BirthdayCouponStrategyImpl();
    private String birthday = "czy666666666666666666666666666666666666666666";

    @Before
    public void before() throws Exception {

        //创造一个
        AccountServiceImpl accountServiceImpl = new AccountServiceImpl(){
            @Override
            public User getUserInfo(int id){
                User user = new User();
                user.setBirthday(birthday);
                return user;
            }
        };

        Field field = birthdayCouponStrategyImpl.getClass().getDeclaredField("accountService");
        field.setAccessible(true);
        field.set(birthdayCouponStrategyImpl, accountServiceImpl);
        //以上3行 相当于 birthdayCouponStrategyImpl.accountService=accountServiceImpl;
        field.setAccessible(false);
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
        orderVO.setUserId(-666);
        Coupon coupon = new Coupon();
        orderVO.setCreateDate("2020-02-29");
        birthday="2020-02-29";
        assert birthdayCouponStrategyImpl.isMatch(orderVO,coupon);
    }

    @Test
    public void testIsMatch02() throws Exception {
        OrderVO orderVO = new OrderVO();
        orderVO.setUserId(-666);
        Coupon coupon = new Coupon();
        orderVO.setCreateDate("2020-02-29");
        birthday="2020-02-28";
        assert ! birthdayCouponStrategyImpl.isMatch(orderVO,coupon);
    }

    @Test
    public void testIsMatch03() throws Exception {
        OrderVO orderVO = new OrderVO();
        orderVO.setUserId(-666);
        Coupon coupon = new Coupon();
        orderVO.setCreateDate("2021-02-29");
        birthday="2020-02-29";
        boolean pass = false;
        try {
            birthdayCouponStrategyImpl.isMatch(orderVO,coupon) ;
        }catch (DateTimeException e){
            System.out.println("test pass:");
            System.out.println(e.getMessage());
            pass = true;
        }
        assert pass;

    }

    @Test
    public void testIsMatch04() throws Exception {
        OrderVO orderVO = new OrderVO();
        orderVO.setUserId(-666);
        Coupon coupon = new Coupon();
        orderVO.setCreateDate("2020-12-14");
        birthday="1999-12-14";
        assert birthdayCouponStrategyImpl.isMatch(orderVO,coupon) ;
    }

} 
