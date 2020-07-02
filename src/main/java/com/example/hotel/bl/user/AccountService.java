package com.example.hotel.bl.user;

import com.example.hotel.enums.UserType;
import com.example.hotel.po.User;
import com.example.hotel.vo.*;

/**
 * @author huwen
 * @date 2019/3/23
 */
public interface AccountService {

    /**
     * 注册账号
     *
     * @return
     */
    ResponseVO registerAccount(UserVO userVO);

    /**
     * 删除账号
     *
     * @return
     */
    ResponseVO deleteUser(int userID);


    /**
     * 用户登录，登录成功会将用户信息保存再session中
     *
     * @return
     */
    User login(UserForm userForm);

    /**
     * 获取用户个人信息
     * @param id
     * @return
     */
    User getUserInfo(int id);

    /**
     * 更新用户个人信息
     * @param id
     * @param password
     * @param username
     * @param phonenumber
     * @return
     */
    ResponseVO updateUserInfo(int id, String password,String username,String phonenumber);


    /**
     * 更新用户信用值
     * @param id
     * @param credit
     * @return
     */
    ResponseVO updateCredit(int id, double credit);

    /**
     * 更新用户职业
     * @param id
     * @param userType
     * @return
     */
    ResponseVO updateUserType(int id, UserType userType);

    /**
     * 根据邮箱获取用户
     * @param email
     * @return
     */
    User getUserByEmail(String email);

}
