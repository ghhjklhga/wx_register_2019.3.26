package com.edu.scau.commom.enums;

public enum DoctorEnum {
//    医生的应该是 住院医师 主治医师 副主任医师 主任医师
//    护士的职称是 护士 护师 主管护师 护士长

    DIRECTOR(1, "主任医师"),
    DEPUTY_DIRECTOR(2, "副主任医师"),
    PHYSICIAN(3, "医师"),
    ATTENDING_PHYSICIAN(4, "主治医师"),
    TECHNICIAN(5, "技师"),
    NURSE_PRACTITIONER(6, "护师"),
    PROFESSOR(7, "教授"),
    NURSE_MASTER(8, "护士长");


    private int code;
    private String desc;

    DoctorEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static String getName(int code){
        for(DoctorEnum doctorEnum: DoctorEnum.values()){
            if (doctorEnum.getCode() == code){
                return doctorEnum.getDesc();
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
