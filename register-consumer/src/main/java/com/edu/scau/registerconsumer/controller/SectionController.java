package com.edu.scau.registerconsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.edu.scau.commom.response.ServerResponse;
import com.edu.scau.registerapi.service.SectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/section")
@Slf4j
public class SectionController {
    @Reference
    private SectionService sectionService;

    @GetMapping("/list")
    public ServerResponse getSectionList(){
        ServerResponse response = sectionService.getSectionList();
        log.info("【获取科室列表】获取成功！结果：{}",response);
        return response;
    }

}
