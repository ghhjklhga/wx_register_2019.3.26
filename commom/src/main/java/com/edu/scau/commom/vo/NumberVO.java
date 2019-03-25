package com.edu.scau.commom.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class NumberVO implements Serializable {
    private Integer id;
    private Integer rest;
    private Integer sum;
    private String time;

    public NumberVO(){}

    public NumberVO(Integer id, Integer rest, Integer sum, String time) {
        this.id = id;
        this.rest = rest;
        this.sum = sum;
        this.time = time;
    }
}
