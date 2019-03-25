package com.edu.scau.registerconsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.edu.scau.commom.response.ServerResponse;
import com.edu.scau.registerapi.service.DistinctService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/distinct")
public class DistinctController {
    @Reference
    private DistinctService distinctService;

    @GetMapping("/list")
    public ServerResponse getDistinct(){
        ServerResponse response =  distinctService.getDistinctAllList();
        log.info("【获取地区】获取地区成功，结果：{}",response);
        return response;
    }
}
