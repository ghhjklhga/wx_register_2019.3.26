package com.edu.scau.commom.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Section implements Serializable {
    private Integer id;
    private String name;
    private Integer no;
}
