package com.edu.scau.commom.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Illness implements Serializable {
    private Integer id;
    private String name;
    private int sectionid;
}
