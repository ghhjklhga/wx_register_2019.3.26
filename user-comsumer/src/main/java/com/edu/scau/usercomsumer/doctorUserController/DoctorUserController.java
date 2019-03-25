package com.edu.scau.usercomsumer.doctorUserController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.edu.scau.commom.enums.ResponseEnum;
import com.edu.scau.commom.response.ServerResponse;
import com.edu.scau.commom.vo.DoctorUserVO;
import com.edu.scau.userapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/doctorUser")
@Slf4j
@CrossOrigin
public class DoctorUserController {
    @Reference
    private UserService userService;

    @PostMapping("/login")
    public ServerResponse Login(@RequestParam("username")String username,
                                @RequestParam("password")String password,
                                HttpServletResponse httpServletResponse
                                ){
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            log.error("【医生用户登录】失败！账号{}或密码{}为空!",username,password);
            return ServerResponse.createByErrorMeeage(ResponseEnum.USERNAME_OR_PASSWORD_NULL.getDesc());
        }
        ServerResponse response = userService.doctorUserLogin(username, password);
        if (response.getCode() == ResponseEnum.SUCCESS.getCode()){
            log.info("【医生用户登录】成功！结果：{}",response);
            //将token设置为请求头
            httpServletResponse.setHeader("userToken", ((DoctorUserVO)response.getData()).getUserToken());
            //去除token
            //response.setData(null);
        }
        return response;
    }

    @PostMapping("/signin")
    public ServerResponse signin(@RequestParam("username")String username,
                                 @RequestParam("password")String password,
                                 @RequestParam("doctorId")Integer doctorId){
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            log.error("【医生用户注册】失败！账号{}或密码{}为空!",username,password);
            return ServerResponse.createByErrorMeeage(ResponseEnum.USERNAME_OR_PASSWORD_NULL.getDesc());
        }
        ServerResponse response = userService.doctorUserSignin(username, password, doctorId);
        log.info("【医生注册成功】成功，{}",response);
        return response;
    }

    @PostMapping("/logout")
    public ServerResponse logout(@RequestHeader("userToken") String token){
        if(StringUtils.isEmpty(token)){
            log.error("【医生用户退出登录】失败！token为空!",token);
            return ServerResponse.createByErrorMeeage(ResponseEnum.TOKEN_EMPTY_ERROR.getDesc());
        }
        ServerResponse response = userService.dockerUserLogout(token);

        return response;
    }
}
