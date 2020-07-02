package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.po.Coupon;
import com.example.hotel.util.CouponMatchStrategyService;
import com.example.hotel.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * @author 黄孟斌
 * @date 2020/05/11
 * 这个类代理了CouponMatchStrategy接口下的其他所有类，对于各种优惠类型的判断函数都应该由它进行转发
 */
@Service
public class CouponMatchStrategyProxy implements CouponMatchStrategy {


    /**
     * 集中管理所有的优惠判断服务类，key是类承担判断的优惠券类型，value是具体的对象引用
     */
    private final Map<Integer,CouponMatchStrategy> strategyMap = new HashMap<>();



    /**
     * 构造方法：     *
     * @param applicationContext spring的上下文
     *
     * 根据上下文获取spring里面所有的bean容器，筛选出贴有  @CouponMatchStrategyService 标记的对象。
     *   通过反射获取 该注解内的value值
     *   将它们其放入{字典}内集中管理
     */
    @Autowired
    public CouponMatchStrategyProxy(ApplicationContext applicationContext) {
        System.out.println("\033[35;0m优惠策略信息如下\033[0m:");
        applicationContext.getBeansWithAnnotation(CouponMatchStrategyService.class).values().stream().map(o->(CouponMatchStrategy)o).forEach(couponMatchStrategy -> {
            final int key = couponMatchStrategy.getClass().getAnnotation(CouponMatchStrategyService.class).value();
            if (strategyMap.get(key) != null) {
                throw new RuntimeException("\033[31;0m怎么出现了两个相同的优惠策略值？\n\033[0m" +
                        "\033[35;0m\tkey=" + key + "\t" +
                        "class=" + strategyMap.get(key).getClass().getSimpleName() + ", " + couponMatchStrategy.getClass().getSimpleName() + "\033[0m");
            } else {
                strategyMap.put(key, couponMatchStrategy);
            }
            System.out.println("\t\033[33;0m" + key + "\t\033[30;0m -> \t\033[36;0m" + couponMatchStrategy.getClass().getSimpleName() + "\033[0m");
        });
    }


    /**
     * @param orderVO 订单
     * @param coupon  优惠券
     * @return 是否匹配
     */
    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon) {
        try {
            return this.strategyMap.get(coupon.getCouponType()).isMatch(orderVO, coupon);
        }
        catch (NullPointerException e){
            e.printStackTrace();
            System.err.println("出现了后端没见过的优惠类型，前端同学是不是传错值了?");
            System.err.println(coupon);
            return false;
        }
    }
}
