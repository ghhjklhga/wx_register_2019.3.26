package com.edu.scau.commom.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class HospitalDetailVO implements Serializable {
    private Integer id;
    private String name;
    private Integer level;
    private String address;
    private String phone;
    private String description;
    private String picture;
}
