package com.edu.scau.commom.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class DoctorOrderVO implements Serializable {
    private String id;
    private Integer visitdate;
    private String visitTime;
    private String username;
    private String userphone;
    private String address;
    private Integer state;
}
