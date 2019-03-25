package com.edu.scau.commom.pojo;

import com.edu.scau.commom.enums.OrderEnum;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderDetail implements Serializable{
    private String id;
    private Integer numberid;

    private Integer visitdate;
    private Integer visittime;

    private Integer userid;
    private String openid;

    private Integer doctorid;
    private Integer hospitalid;
    private Integer departmentid;

    private Date ordertime;

    private Integer bookway;
    private BigDecimal price;

    private Integer state = OrderEnum.NOT_PAY.getCode();

    private Integer Type;
    private String cancelreason;
}
