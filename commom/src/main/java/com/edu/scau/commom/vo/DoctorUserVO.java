package com.edu.scau.commom.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class DoctorUserVO implements Serializable {
    private Integer id;
    private String name;
    private String userToken;
}
