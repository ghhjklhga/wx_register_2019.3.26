package com.edu.scau.commom.enums;

public enum ExceptionCodeEnum {

    NULL_PARAMETER(1, "参数丢失");

    private int code;
    private String desc;

    ExceptionCodeEnum(int code, String desc){
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
