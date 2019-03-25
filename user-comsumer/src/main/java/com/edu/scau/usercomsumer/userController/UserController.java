package com.edu.scau.usercomsumer.userController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.edu.scau.commom.enums.ResponseEnum;
import com.edu.scau.commom.form.UserForm;
import com.edu.scau.commom.response.ServerResponse;
import com.edu.scau.userapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Reference
    private UserService userService;

    @PostMapping("/userinfo/insert")
    public ServerResponse getUserInfo(@Valid UserForm userForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.error("【填写用户信息】参数传入失败！结果：{}",userForm);
            return ServerResponse.createByErrorMeeage(ResponseEnum.USER_ERROR_PARAM.getDesc());
        }
        return userService.signin(userForm);
    }

    @PostMapping("/userinfo/select")
    public ServerResponse getUserInfo(@Param("openid") String openid){
        if (StringUtils.isEmpty(openid)){
            log.error("【获取用户信息】参数传入失败！结果：{}",openid);
            return ServerResponse.createByErrorMeeage(ResponseEnum.USER_ERROR_PARAM.getDesc());
        }
        return userService.getUserInfoByOpenid(openid);
    }
    @GetMapping("/list")
    public ServerResponse login(){

        log.info("user list");

        ServerResponse result = userService.getUserAll();
        return result;
    }

    @PostMapping("/info")
    public ServerResponse getUserinfo(@Param("openid")String openid){
        if (StringUtils.isEmpty(openid)){
            log.error("【获取用户信息】参数传入失败！结果：{}",openid);
            return ServerResponse.createByErrorMeeage(ResponseEnum.USER_ERROR_PARAM.getDesc());
        }
        ServerResponse response = userService.getUserInfoByOpenid(openid);
        log.info("【获取用户信息】获取成功！结果: {}",response);
        return response;
    }
}
