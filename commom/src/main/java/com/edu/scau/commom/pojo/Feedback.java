package com.edu.scau.commom.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
public class Feedback implements Serializable {
    private Integer id;
    private String title;
    private String context;
    private String contact;
    private Date time;
    private int userid;
}
