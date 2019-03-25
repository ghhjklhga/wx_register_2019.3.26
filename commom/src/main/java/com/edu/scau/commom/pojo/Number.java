package com.edu.scau.commom.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Number implements Serializable {
    private Integer id;
    private Integer date;
    private Integer time;
    private Integer sum;
    private Integer rest;
    private Integer doctorid;
}
