package com.example.hotel.data.user;

import com.example.hotel.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author 13524
 */
@Mapper
@Repository
public interface AccountMapper {

    /**
     * 创建一个新的账号
     *
     * @return int
     */
     int createNewAccount(User user);

    /**
     * 根据用户名查找账号
     * @return User
     */
     User getAccountByName(@Param("email") String email);

     User getAccountById(@Param("id") int id);

    /**
     * 更新用户信息
     * @param id - int
     * @param username - String
     * @param phonenumber - String
     * @return - int
     */
     int updateAccountBaseInfo(@Param("id") int id, @Param("userName") String username, @Param("phoneNumber") String phonenumber);

     void deleteAccount(@Param("id")int id);
    /**
     * 更新用户信用值
     * @param id - int
     * @param credit - double
     */
    void updateCredit(@Param("id")int id, @Param("credit")double credit);

    /**
     * 更新用户职业
     * @param id - int
     * @param usertype - String
     */
    void updateUserType(@Param("id")int id, @Param("usertype")String usertype);
}
