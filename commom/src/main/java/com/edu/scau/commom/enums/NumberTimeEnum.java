package com.edu.scau.commom.enums;

public enum NumberTimeEnum {

    FORENOON(10, "上午"),
    AFTERNOON(20, "下午"),


    EIGHT_TO_EIGHT_POINTFIVE(1, "8:00-8:30"),
    EIGHT_POINTFIVE_TO_NINE(2, "8:30-9:00"),
    NINE_TO_NINE_POINTFIVE(3, "9:00-9:30"),
    NINE_POINTFIVE_TO_TEN(4, "9:30-9:00"),
    TEN_TO_TEN_POINTFIVE(5, "10:00-10:30"),
    TEN_POINTFIVE_TO_ELEVEN(6, "10:30-11:00"),
    ELEVEN_TO_ELEVEN_POINTFIVE(7, "11:00-11:30"),
    ELEVEN_POINTFIVE_TO_TWELVE(8, "11:30-12:00"),

    FOURTEEN_POINTFIVE_TO_FIFTEEN(21, "14:30-15:00"),
    FIFTEETN_TO_FIFTEEN_POINTFIVE(22, "15:00-15:30"),
    FIFTEEN_POINTFIVE_TO_SIXTEEN(23, "15:30-16:00"),
    SIXTEEN_TO_SIXTEEN_POINTFIVE(24, "16:00-16-30"),
    SIXTEEN_POINTFIVE_TO_SEVENTEEN(25, "16:30-17:00");


    private int code;
    private String time;

    NumberTimeEnum(int code, String time){
        this.code = code;
        this.time = time;
    }

    public static String getTime(int code){
        for(NumberTimeEnum numberTimeEnum: NumberTimeEnum.values()){
            if (numberTimeEnum.getCode() == code){
                return numberTimeEnum.getTime();
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }
    public String getTime() {
        return time;
    }
}
