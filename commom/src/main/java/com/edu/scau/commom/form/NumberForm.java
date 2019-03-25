package com.edu.scau.commom.form;

import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class NumberForm {
    @NonNull
    private Date time;
    @NonNull
    private Integer sum;
    @NonNull
    private BigDecimal price;
    @NonNull
    private Integer doctorid;
}
