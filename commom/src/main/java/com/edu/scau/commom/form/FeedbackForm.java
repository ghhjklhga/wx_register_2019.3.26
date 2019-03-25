package com.edu.scau.commom.form;

import lombok.Data;

import java.io.Serializable;

@Data
public class FeedbackForm implements Serializable {
    private String title;
    private String context;
    private String contact;
    private Integer userid;
}
