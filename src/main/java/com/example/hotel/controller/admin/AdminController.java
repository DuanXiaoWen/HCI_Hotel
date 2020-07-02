package com.example.hotel.controller.admin;

import com.example.hotel.bl.admin.AdminService;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@RestController()
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("/addManager")
    public ResponseVO addManager(@RequestBody UserForm userForm){

        return adminService.addManager(userForm);
    }

    @PostMapping("/getAllManagers")
    public ResponseVO getAllManagers(){
        return ResponseVO.buildSuccess(adminService.getAllManagers());
    }


    @PostMapping("/deleteUser")
    public ResponseVO deleteUser(@RequestBody UserVO userVO){
        return adminService.deleteManager(userVO);
    }

    @PostMapping("/changeUserType")
    public ResponseVO changeUserType(@RequestBody UserForm userForm){
        return adminService.changeUserType(userForm);
    }
}
