package com.edu.scau.commom.pojo;

import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;

@Data
public class Department implements Serializable {
    private Integer id;
    private String name;
    private Integer hospitalid;
}
