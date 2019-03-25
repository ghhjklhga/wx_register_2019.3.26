package com.edu.scau.commom.enums;

import lombok.Data;

public enum  WeedEnum {
    MONDAY(1 , "星期一"),
    TUESDAY(2, "星期二"),
    WENESDAY(3, "星期三"),
    THURSDAY(4, "星期四"),
    FRIDAY(5, "星期五"),
    SATUSDAY(6, "星期六"),
    SUMDAY(7, "星期天"),
    ALL(8, "所有");

    private int code;
    private String desc;

    WeedEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static String getWeed(int code){
        for(WeedEnum weedEnum: WeedEnum.values()){
            if (weedEnum.getCode() == code){
                return weedEnum.getDesc();
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }
    public String getDesc() {
        return desc;
    }
}

