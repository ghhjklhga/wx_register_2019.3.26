package com.edu.scau.commom.pojo;

import com.edu.scau.commom.enums.DoctorStateEnum;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Doctor implements Serializable {
    private Integer id;
    private Integer level;
    private String name;
    private String sex;
    private Integer age;
    private BigDecimal price;
    private Integer state = DoctorStateEnum.NORMAL.getCode();
    private Integer departmentid;
    private String departmentname;
    private Integer hospitalid;
    private String hospitalname;
    private Integer sectionid;
    private String description;
    private String picture;
}
