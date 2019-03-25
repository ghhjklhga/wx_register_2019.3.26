package com.edu.scau.commom.enums;

public enum HospitalEnum {

    FIRST_GRADE(1, "一甲医院"),
    SECONDE_GRADE(2, "二甲医院"),
    THIRD_GRADE(3, "三甲医院");

    private int code;
    private String desc;

    HospitalEnum(int code, String desc){
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
