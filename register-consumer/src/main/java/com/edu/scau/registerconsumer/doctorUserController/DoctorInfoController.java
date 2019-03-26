package com.edu.scau.registerconsumer.doctorUserController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.edu.scau.api.redisapi.api.RedisService;
import com.edu.scau.commom.pojo.DoctorUser;
import com.edu.scau.commom.response.ServerResponse;
import com.edu.scau.registerapi.service.DoctorInfoService;
import com.edu.scau.registerapi.service.NumberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/doctorInfo")
public class DoctorInfoController {
    @Reference
    private DoctorInfoService doctorInfoService;
    @Reference
    private NumberService numberService;
    @Reference
    private RedisService redisService;

    //获取医生用户的所有号码列表 返回包括状态是否就诊
    @PostMapping("/number/all")
    public ServerResponse getDoctorNumberAll(@RequestHeader("userToken")String token){
        DoctorUser doctorUser = (DoctorUser) redisService.get(token);
        ServerResponse response = numberService.getNumberByDoctorid(doctorUser.getId());
        log.info("【获取医生用户的所有号码】获取成功！");
        return response;
    }

    //临时停诊，修改医生状态，把停诊期间未完成的订单修改为取消
    @PostMapping("/close")
    public ServerResponse closeDoctorState(@RequestHeader("userToken")String token,
                                           @RequestParam("visitdate")Integer visitdate,
                                           @RequestParam("title")String title,
                                           @RequestParam("context")String context){
        ServerResponse response = doctorInfoService.closeDoctor(token,visitdate,title,context);
        return response;
    }

    //开诊，修改医生状态，
    @PostMapping("/open")
    public ServerResponse openDoctorState(@RequestHeader("userToken")String token){
        ServerResponse response = doctorInfoService.openDoctorByDid(token);
        return response;
    }
}
