package com.example.hotel.enums;

/**
 * @author hmb
 * 商圈
 */

public enum BizRegion {

    //西单
    XiDan("西单");

    private String value;

    BizRegion(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
