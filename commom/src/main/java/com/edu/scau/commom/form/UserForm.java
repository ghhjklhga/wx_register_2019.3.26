package com.edu.scau.commom.form;

import lombok.Data;
import lombok.NonNull;

@Data
public class UserForm {
    @NonNull
    private String username;
    @NonNull
    private String phone;
    @NonNull
    private String idnumber;
    @NonNull
    private String address;
    @NonNull
    private String openid;
}
