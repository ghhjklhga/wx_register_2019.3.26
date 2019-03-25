package com.edu.scau.commom.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SectionChildVO implements Serializable {
    private Integer child_id;
    private String child_name;

    public SectionChildVO(){}

    public SectionChildVO(Integer child_id, String child_name){
        this.child_id = child_id;
        this.child_name = child_name;
    }
}
