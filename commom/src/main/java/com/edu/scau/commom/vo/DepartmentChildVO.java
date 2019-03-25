package com.edu.scau.commom.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class DepartmentChildVO implements Serializable {
    private Integer child_id;
    private String child_name;

    public DepartmentChildVO(Integer child_id, String child_name) {
        this.child_id = child_id;
        this.child_name = child_name;
    }
}
