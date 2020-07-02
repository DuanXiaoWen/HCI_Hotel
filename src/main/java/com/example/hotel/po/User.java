package com.example.hotel.po;


import com.example.hotel.enums.UserType;
import lombok.*;

@Data
public class User {
    private Integer id;
    private String email;
    private String password;
    private String userName;
    private String phoneNumber;
    private Double credit;
    private UserType userType;
    private String birthday;

}
