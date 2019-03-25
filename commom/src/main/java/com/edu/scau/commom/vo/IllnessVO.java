package com.edu.scau.commom.vo;

import com.edu.scau.commom.pojo.Illness;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class IllnessVO implements Serializable {
    private Integer sectionid;
    private String sectionname;

    private List<Illness> illnessList;
}
