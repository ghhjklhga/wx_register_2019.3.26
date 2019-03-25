package com.edu.scau.commom.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SectionVO implements Serializable {
    private Integer sec_id;
    private String sec_name;
    private List<SectionChildVO> sectionChildVOList;
}
