package com.edu.scau.registerconsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.edu.scau.commom.response.ServerResponse;
import com.edu.scau.registerapi.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
@Slf4j
public class DepartmentController {
    @Reference
    private DepartmentService departmentService;

    @GetMapping("/list")
    public ServerResponse getDepartmentList(@Param("hid") Integer hid){
        ServerResponse response = departmentService.getDepartmentlistByHid(hid);
        log.info("【获取医院门诊列表】获取成功！结果：{}",response);
        return response;
    }
}
