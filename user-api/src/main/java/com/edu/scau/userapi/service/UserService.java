package com.edu.scau.userapi.service;

import com.edu.scau.commom.form.UserForm;
import com.edu.scau.commom.pojo.User;
import com.edu.scau.commom.response.ServerResponse;

public interface UserService {

    ServerResponse signin(UserForm userForm);

    ServerResponse getUserInfoByOpenid(String openid);

    ServerResponse getUserAll();

    //  供其他Provicer调用
    User getUserByOpenid(String openid);

    //医生用户登录
    ServerResponse doctorUserLogin(String username, String password);

    //录入医生
    ServerResponse doctorUserSignin(String username, String password, Integer doctorId);

    //医生退出登录
    ServerResponse dockerUserLogout(String token);
}
