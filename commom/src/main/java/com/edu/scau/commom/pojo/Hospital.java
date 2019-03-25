package com.edu.scau.commom.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table
public class Hospital implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer distinctid;
    private String name;
    private Integer level;
    private String address;
    private String phone;
    private String description;
    private String picture;
}
