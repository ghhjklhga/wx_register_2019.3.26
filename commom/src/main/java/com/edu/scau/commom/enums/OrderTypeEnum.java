package com.edu.scau.commom.enums;

import lombok.Data;

public enum OrderTypeEnum {
    ORDER_FOR_MYSELF(0, "为自己挂号"),
    ORDER_FOR_CHILD(1, "为子女挂号");

    private int code;
    private String desc;

    OrderTypeEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }
    public String getDesc() {
        return desc;
    }
}
