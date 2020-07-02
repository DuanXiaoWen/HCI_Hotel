package com.example.hotel.bl.admin;

import com.example.hotel.po.User;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.UserVO;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
public interface AdminService {

    /**
     * 添加酒店管理人员账号
     * @param userForm
     * @return
     */
    ResponseVO addManager(UserForm userForm);

    /**
     * 删除酒店管理人员账号
     * @param userVO
     * @return
     */
    ResponseVO deleteManager(UserVO userVO);

    /**
     * 添加酒店管理人员账号
     * @param userForm
     * @return
     */
    ResponseVO changeUserType(UserForm userForm);

    /**
     * 获得所有酒店管理人员信息
     * @return
     */
    List<User> getAllManagers();

}
