package com.edu.scau.commom.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Notice implements Serializable {
    private Integer id;
    private String title;
    private String context;
    private Date time;
}
