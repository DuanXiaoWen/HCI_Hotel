package com.example.hotel.vo;

import com.example.hotel.enums.UserType;
import lombok.*;

/**
 * @author fjj
 * @date 2019/4/11 3:22 PM
 */
@Data
public class UserVO {
    private Integer id;
    private String email;
    private String password;
    private String userName;
    private String phoneNumber;
    private double credit;
    private UserType userType;
    private String birthday;

}
