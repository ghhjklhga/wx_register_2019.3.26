package com.edu.scau.commom.dto;

import lombok.Data;

@Data
public class WXSessionDTO {
    private String openid;
    private String session_key;
}
