package com.edu.scau.commom.enums;

public enum OrderEnum {

    ALL_ORDER(0, "所有订单"),
    NOT_PAY(1, "已预约未付款"),
    ALREADY_PAY(2, "已付款未就诊"),
    ALREADY_COMPLETE(3, "已完成"),
    ALREADY_CANCEL(4, "已取消");


    private int code;
    private String desc;

    OrderEnum(int code, String desc){
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
