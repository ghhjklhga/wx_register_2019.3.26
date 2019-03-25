package com.edu.scau.commom.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class StarDoctor implements Serializable {
    private Integer id;
    private Integer userid;
    private String openid;
    private Integer doctorid;
}
