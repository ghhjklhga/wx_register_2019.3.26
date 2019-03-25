package com.edu.scau.commom.form;

import lombok.Data;

import java.io.Serializable;

@Data
public class NoticeForm implements Serializable {
    private String title;
    private String context;
}
