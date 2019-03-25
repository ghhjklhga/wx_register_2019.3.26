package com.edu.scau.commom.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class HospitalVO implements Serializable {
    private Integer id;
    private String name;
    private Integer level;
    private String address;
    private String phone;
    private String picture;
    private String description;

    public HospitalVO(){}

    public HospitalVO(Integer id, String name, Integer level, String address, String phone, String picture, String description) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.address = address;
        this.phone = phone;
        this.picture = picture;
        this.description = description;
    }
}
