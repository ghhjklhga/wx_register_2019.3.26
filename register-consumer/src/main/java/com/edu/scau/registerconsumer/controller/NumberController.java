package com.edu.scau.registerconsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.edu.scau.commom.enums.ResponseEnum;
import com.edu.scau.commom.response.ServerResponse;
import com.edu.scau.registerapi.service.NumberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/number")
@Slf4j
public class NumberController {
    @Reference
    private NumberService numberService;

    @GetMapping("/list")
    public ServerResponse getNumberListDay(@RequestParam("doctorId") Integer doctorId,@Param("day")Integer day){
        log.info("获取号码：doctorId: {}",doctorId);
        ServerResponse response = numberService.getNumberByDidAndDay(doctorId, day);
        log.info("【获取号码列表】获取成功！结果：{}",response);
        return response;
    }

    @GetMapping("/list/all")
    public ServerResponse getNumberListAll(@RequestParam("doctorId") Integer doctorId){
        log.info("获取号码：doctorId: {}",doctorId);
        ServerResponse response = numberService.getNumberByDoctorid(doctorId);
        log.info("【获取号码列表】获取成功！结果：{}",response);
        return response;
    }

    @GetMapping("/detail")
    public ServerResponse getNumberDetail(@RequestParam("numberId") Integer numberId){
        if (numberId == null){
            log.error("【获取号码信息】controller获取前端numberId失败！");
            return ServerResponse.createByErrorMeeage(ResponseEnum.NUMBER_ERROR_PARAM.getDesc());
        }
        ServerResponse response = numberService.getNumberById(numberId);
        log.info("【获取号码信息】获取成功！结果：{}",response);
        return response;
    }


}
