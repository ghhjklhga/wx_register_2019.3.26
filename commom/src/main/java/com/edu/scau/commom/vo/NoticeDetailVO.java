package com.edu.scau.commom.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class NoticeDetailVO implements Serializable {
    private Integer id;
    private String title;
    private String context;
    private Date time;

    public NoticeDetailVO() {
    }

    public NoticeDetailVO(Integer id, String title, String context, Date time) {
        this.id = id;
        this.title = title;
        this.context = context;
        this.time = time;
    }
}
