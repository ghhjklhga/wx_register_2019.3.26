package com.edu.scau.commom.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String phone;
    private String idnumber;
    private String address;
    private String openid;
}
