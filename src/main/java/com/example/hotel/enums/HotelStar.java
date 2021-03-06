package com.example.hotel.enums;

/**
 * 酒店星级
 * @author hmb
 *
 */

public enum HotelStar {

    //各种星级
    Five("五星级"),
    Four("四星级"),
    Three("三星级");

    private String value;

    HotelStar(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
