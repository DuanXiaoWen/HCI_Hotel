package com.example.hotel.blImpl.order;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.data.order.OrderMapper;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.po.Order;
import com.example.hotel.po.User;
import com.example.hotel.vo.CommentVO;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@Service
public class OrderServiceImpl implements OrderService {
    private final static String RESERVE_ERROR = "预订失败";
    private final static String DATE_ERROR_Reserve = "宁怎么能预订今天之前的房间???";
    private final static String DATE_ERROR_CheckIn = "宁怎么来这么晚？？";
    private final static String CREDIT_LACK = "宁的信用分不够了,赶紧充钱吧";
    private final static String ROOM_NUMBER_LACK = "预订房间数量剩余不足";
    private final static String ANNUl_ERROR = "删除订单失败";
    private final static String UPDATE_ERROR = "修改失败";

    private final static String yu_ding = "已预订" ;
    private final static String che_xiao = "已撤销" ;
    private final static String check_in = "已执行" ;
    private final static String check_out = "已退房" ;
    private final static String ping_jia = "已评价" ;
    private final static String yi_chang = "异常" ;





    //private final static String = "" ;

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    HotelService hotelService;
    @Autowired
    AccountService accountService;
    @Autowired
    AccountMapper accountMapper;
    @Override
    public ResponseVO addOrder(OrderVO orderVO) {

        if(accountService.getUserInfo(orderVO.getUserId()).getCredit()<=0){
            return ResponseVO.buildFailure(CREDIT_LACK);
        }
        int reserveRoomNum = orderVO.getRoomNum();//需要的数量
        int totalNum = hotelService.getRoomTotalNum(orderVO.getHotelId(),orderVO.getRoomType());//该酒店，这种房间的总数量
        int usedNum = this.getUsedNum(orderVO);
        int validNum = totalNum - usedNum;
        if(reserveRoomNum > validNum){
            return ResponseVO.buildFailure(ROOM_NUMBER_LACK);
        }
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            String curdate = sf.format(date);
            if(curdate.compareTo(orderVO.getCheckInDate())>0){
                return ResponseVO.buildFailure(DATE_ERROR_Reserve);
            }
            orderVO.setCreateDate(curdate);
            orderVO.setOrderState(yu_ding);
            User user = accountService.getUserInfo(orderVO.getUserId());
            //orderVO.setTenantName(orderVO.getTenantName());
            orderVO.setPhoneNumber(user.getPhoneNumber());
            Order order = new Order();
            BeanUtils.copyProperties(orderVO,order);
            orderMapper.addOrder(order);
            //hotelService.updateRoomInfo(orderVO.getHotelId(),orderVO.getRoomType(),orderVO.getRoomNum());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(RESERVE_ERROR);
        }
       return ResponseVO.buildSuccess(true);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderMapper.getAllOrders();
    }

    @Override
    public List<Order> getUserOrders(int userId) {
        return orderMapper.getUserOrders(userId);
    }

    @Override
    public List<Order> getHotelOrders(int hotelId) {

        return orderMapper.getHotelOrders(hotelId);
    }

    @Override
    public ResponseVO annulOrder(int orderId) {
        // 取消订单逻辑的具体实现（注意可能有和别的业务类之间的交互
        try{
            Order order=orderMapper.getOrderById(orderId);
            if(order.getOrderState().equals(che_xiao))
                return ResponseVO.buildFailure(ANNUl_ERROR);
            orderMapper.updateOrderState(orderId, che_xiao);
            if(notRevocable(order)){
                User user = accountService.getUserInfo(order.getUserId());
                user.setCredit(user.getCredit()-order.getPrice()/2);
                accountService.updateCredit(order.getUserId(),user.getCredit()-order.getPrice()/2);
            }
            //hotelService.updateRoomInfo(order.getHotelId(),order.getRoomType(),-order.getRoomNum());
        }catch(Exception e){
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(ANNUl_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }





    private boolean notRevocable(Order order){  //不可撤销，指要扣分的意思。

        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date curTime = new Date(System.currentTimeMillis());
        String latestAnnulTime=order.getCheckInDate()+" 08:00:00";//14:00为订单最晚执行时间
        try{
            int result=curTime.compareTo(sf.parse(latestAnnulTime));
            return result>0;
        }catch(ParseException e){
            e.printStackTrace();
        }
        return false;
    }

    private int getUsedNum(OrderVO orderVO){
        List<Order> orderList = this.getAllOrders().stream()
                .filter(o -> o.getHotelId().equals(orderVO.getHotelId()))
                .filter(o -> o.getRoomType().equals(orderVO.getRoomType()))
                .filter(o -> o.getOrderState().equals(yu_ding) || o.getOrderState().equals(check_in)|| o.getOrderState().equals(yi_chang))
                .collect(Collectors.toList());

        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        final long oneDay = 1000 * 60 * 60 * 24L;
        try {
            long checkInDate = sf.parse(orderVO.getCheckInDate()).getTime();
            long checkOutDate = sf.parse(orderVO.getCheckOutDate()).getTime();
            int result = 0;
            for(long date = checkInDate; date < checkOutDate; date += oneDay ){
                int tmp = 0;
                for(Order o : orderList){
                    if(sf.parse(o.getCheckInDate()).getTime() <= date && date < sf.parse(o.getCheckOutDate()).getTime()){
                        tmp += o.getRoomNum();
                    }
                }
                result = Math.max(result, tmp);
            }
            return result;
        }
        catch (ParseException e){
            e.printStackTrace();
            return Integer.MAX_VALUE;
        }
    }

    @Override
    public ResponseVO addComment(OrderVO orderVO) {
        orderMapper.addComment(orderVO.getId(),orderVO.getComment(),orderVO.getCommentScore());
        orderMapper.updateOrderState(orderVO.getId(),ping_jia);
        hotelService.addComment(orderVO.getHotelId(),orderVO.getCommentScore());
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO getCommentByOrderId(Integer orderId) {
        Order order = orderMapper.getOrderById(orderId);
        return ResponseVO.buildSuccess(orderToComment(order));
    }


    @Override
    public List<CommentVO> getHotelComment(Integer hotelId) {
        return this.getAllOrders().stream()
                .filter(order -> order.getHotelId().equals(hotelId))
                .filter(order -> order.getOrderState().equals(ping_jia))
                .map(this::orderToComment)
                .collect(Collectors.toList());
    }


    private CommentVO orderToComment(Order order){
        CommentVO commentVO = new CommentVO();
        commentVO.setComment(order.getComment());
        commentVO.setCommentScore(order.getCommentScore());
        commentVO.setCheckOutTime(order.getCheckOutDate());
        commentVO.setUserName(accountService.getUserInfo(order.getUserId()).getUserName());
        return commentVO;
    }
    public ResponseVO checkIn(int id){
        try{
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            String curdate = sf.format(date);

            Order order=orderMapper.getOrderById(id);

            if(curdate.compareTo(order.getCheckOutDate())>=0){
                return ResponseVO.buildFailure(DATE_ERROR_CheckIn);
            }

            User user = accountService.getUserInfo(order.getUserId());
            user.setCredit(user.getCredit()+order.getPrice());
            orderMapper.updateOrderState(id, check_in);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return ResponseVO.buildSuccess(true);
    }
    public ResponseVO checkOut(int id){
        try{

            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            String outdate = sf.format(date);
            orderMapper.updateOutTime(id,outdate);
            Order out=orderMapper.getOrderById(id);
            orderMapper.updateOrderState(id, check_out);
            //System.out.println(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public List<Order> getHotelAbOrders(Integer hotelId){
        return orderMapper.getHotelAbOrders(hotelId);
    }


    @Test
    public void testNotRevocable01(){
        //这个在{2020-06-28 08:00 beijing}之后测试才能成功
        Order order = new Order();
        order.setCheckInDate("2020-06-28");
        Assert.assertTrue(OrderServiceImpl.this.notRevocable(order));
    }

    @Test
    public void testNotRevocable02(){
        Order order = new Order();
        order.setCheckInDate("2020-06-30");
        Assert.assertFalse(OrderServiceImpl.this.notRevocable(order));
    }


}
