package com.edu.scau.commom.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class NoticeMasterVO implements Serializable {
    private Integer id;
    private String title;
    private Date time;

    public NoticeMasterVO() {
    }

    public NoticeMasterVO(Integer id, String title, Date time) {
        this.id = id;
        this.title = title;
        this.time = time;
    }
}
