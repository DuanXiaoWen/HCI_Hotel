package com.example.hotel.enums;

public enum BizRegion {
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
