package com.edu.scau.commom.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class NumberDayVO implements Serializable {
    private Integer id;
    private String day;
    private List<NumberVO> numberVOList;
}
