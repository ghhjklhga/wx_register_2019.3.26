package com.edu.scau.commom.vo;

import com.edu.scau.commom.enums.OrderEnum;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderMasterVO implements Serializable {
    private String id;
    private Integer userid;
    private String openid;
    private Integer doctorid;
    private String doctorname;
    private Integer hospitalid;
    private String hospitalname;
    private String hospitalpicture;
    private String visitdate;
    private String visittime;
//    private Integer bookway;
    private BigDecimal price;
    private Integer state = OrderEnum.NOT_PAY.getCode();

}
