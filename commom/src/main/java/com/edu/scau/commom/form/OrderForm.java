package com.edu.scau.commom.form;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class OrderForm implements Serializable{
//    @NonNull
    private Integer numberid;
//    @NonNull
    private Integer doctorid;
//    @NonNull
    private Integer hospitalid;
//    @NonNull
    private Integer departmentid;
//    @NonNull
    private String openid;

    public OrderForm() {
    }

//    public OrderForm(Integer numberid, Integer doctorid, Integer hospitalid, Integer departmentid, Integer visittime, String openid) {
//        this.numberid = numberid;
//        this.doctorid = doctorid;
//        this.hospitalid = hospitalid;
//        this.departmentid = departmentid;
//        this.visittime = visittime;
//        this.openid = openid;
//    }
//
//    public Integer getNumberid() {
//        return numberid;
//    }
//
//    public void setNumberid(Integer numberid) {
//        this.numberid = numberid;
//    }
//
//    public Integer getDoctorid() {
//        return doctorid;
//    }
//
//    public void setDoctorid(Integer doctorid) {
//        this.doctorid = doctorid;
//    }
//
//    public Integer getHospitalid() {
//        return hospitalid;
//    }
//
//    public void setHospitalid(Integer hospitalid) {
//        this.hospitalid = hospitalid;
//    }
//
//    public Integer getDepartmentid() {
//        return departmentid;
//    }
//
//    public void setDepartmentid(Integer departmentid) {
//        this.departmentid = departmentid;
//    }
//
//    public Integer getVisittime() {
//        return visittime;
//    }
//
//    public void setVisittime(Integer visittime) {
//        this.visittime = visittime;
//    }
//
//    public String getOpenid() {
//        return openid;
//    }
//
//    public void setOpenid(String openid) {
//        this.openid = openid;
//    }
//
//    @Override
//    public String toString() {
//        return "OrderForm{" +
//                "numberid=" + numberid +
//                ", doctorid=" + doctorid +
//                ", hospitalid=" + hospitalid +
//                ", departmentid=" + departmentid +
//                ", visittime=" + visittime +
//                ", openid='" + openid + '\'' +
//                '}';
//    }
}
