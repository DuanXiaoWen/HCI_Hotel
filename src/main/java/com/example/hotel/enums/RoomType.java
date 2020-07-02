package com.example.hotel.enums;

/**
 * 房型
 * @author hmb
 */

public enum RoomType {

    //各种房间
    BigBed("大床房"),
    DoubleBed("双床房"),
    Family("家庭房");
    private String value;

    RoomType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
