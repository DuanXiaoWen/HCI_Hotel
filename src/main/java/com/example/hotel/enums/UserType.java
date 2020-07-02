package com.example.hotel.enums;

/**
 * @Author: chenyizong
 * @Date: 2020-02-29
 */
public enum UserType {

    //普通人
    Client("1"),

    //酒店管理
    HotelManager("2"),

    //系统管理，大管理员
    Manager("3"),

    //网站营销人。基本没用
    WebMarketer("4");
    private String value;

    UserType(String value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return value;
    }

}
