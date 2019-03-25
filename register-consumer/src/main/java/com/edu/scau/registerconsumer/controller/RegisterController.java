package com.edu.scau.registerconsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.edu.scau.commom.enums.ResponseEnum;
import com.edu.scau.commom.pojo.Distinct;
import com.edu.scau.commom.response.ServerResponse;
import com.edu.scau.registerapi.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/search")
public class RegisterController {

    @GetMapping("/testRegister")
    public ServerResponse test(){
        return ServerResponse.createBySuccessMessage("get register");
    }

}
