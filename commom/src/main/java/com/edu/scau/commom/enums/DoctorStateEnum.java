package com.edu.scau.commom.enums;

public enum DoctorStateEnum {
    STOP(0, "停诊"),
    NORMAL(1, "正常");

    private int code;
    private String desc;

    DoctorStateEnum(int code, String desc) {
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
