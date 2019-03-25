package com.edu.scau.commom.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class DoctorUser implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private Integer doctorid;
}
