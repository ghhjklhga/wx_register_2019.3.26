package com.edu.scau.commom.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class DoctorVO implements Serializable {
    private Integer id;
    private String position;
    private String name;
    //  状态：1正常/0停诊，
    private Integer state;
    private boolean isHavaNumber;
    private String sex;
    private BigDecimal price;
    private Integer departmentid;
    private String departmentname;
    private String picture;
}
