package test.com.example.hotel.blImpl.order; 

import com.example.hotel.blImpl.order.OrderServiceImpl;
import com.example.hotel.po.Order;
import com.example.hotel.vo.OrderVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/** 
* OrderServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>七月 2, 2020</pre> 
* @version 1.0 
*/ 
public class OrderServiceImplTest {

    private OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
    private Method notRevocable;
    private Method getUsedNum;

    @Before
    public void before() throws Exception {
        notRevocable = OrderServiceImpl.class.getDeclaredMethod("notRevocable", Order.class);
        notRevocable.setAccessible(true);
        //强行把某个方法设置成public

        getUsedNum = OrderServiceImpl.class.getDeclaredMethod("getUsedNum", OrderVO.class);
        getUsedNum.setAccessible(true);
        //强行把某个方法设置成public
    }

    @After
    public void after() throws Exception {
    }


    /**
    *
    * Method: notRevocable(Order order)
    *
    */
    @Test
    public void testNotRevocable01() throws Exception {
        Order order = new Order();
        order.setCheckInDate("2019-06-27");
        assert (notRevocable.invoke(orderServiceImpl,order).equals(true));
    }

    @Test
    public void testNotRevocable02() throws Exception {
        Order order = new Order();
        order.setCheckInDate("2021-06-27");
        assert (notRevocable.invoke(orderServiceImpl,order).equals(false));
    }



} 
