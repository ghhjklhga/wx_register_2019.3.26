package com.edu.scau.commom.vo;

import com.edu.scau.commom.enums.OrderEnum;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderDetailVO implements Serializable {
    private String id;
    private Integer numberid;

    private Integer visitdate;
    private String visittime;

    private Integer userid;
    private String username;
    private String phone;
    private String openid;

    private Integer doctorid;
    private String doctorname;

    private Integer hospitalid;
    private String hospitalname;
    private String hospitalpicture;

    private Integer departmentid;
    private String departmentname;

    private String ordertime;

    private Integer bookway;
    private BigDecimal price;

    private Integer state;

    private Integer Type;
    private String cancelreason;
}
