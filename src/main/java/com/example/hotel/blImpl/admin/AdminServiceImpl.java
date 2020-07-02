package com.example.hotel.blImpl.admin;

import com.example.hotel.bl.admin.AdminService;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.data.admin.AdminMapper;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.User;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@Service
public class AdminServiceImpl implements AdminService {
    private final static String ACCOUNT_EXIST = "账号已存在";
    private final static String SAME_POST = "同样的职位";
    private final static String HAS_OWNED_HOTEL = "还在管理酒店";
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    AccountService accountService;
    @Autowired
    HotelService hotelService;

    @Override
    public ResponseVO addManager(UserForm userForm) {
        User user = new User();
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setUserType(userForm.getUserType());
        user.setCredit(67);
        try {
            adminMapper.addManager(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(ACCOUNT_EXIST);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public List<User> getAllManagers() {
        return adminMapper.getAllManagers();
    }

    @Override
    public ResponseVO deleteManager(UserVO userVO) {
        return accountService.deleteUser(userVO.getId());
    }

    @Override
    public ResponseVO changeUserType(UserForm userForm) {
        User user = accountService.getUserInfo(userForm.getId());

        if (userForm.getUserType() == user.getUserType()) {
            return ResponseVO.buildFailure(SAME_POST);
        }

        if (user.getUserType() == UserType.HotelManager) {
            int ownNum = hotelService.retrieveMgrHotels(user.getId()).size();
            if (ownNum > 0)
                return ResponseVO.buildFailure(HAS_OWNED_HOTEL);
        }

        return accountService.updateUserType(userForm.getId(), userForm.getUserType());
    }
}
