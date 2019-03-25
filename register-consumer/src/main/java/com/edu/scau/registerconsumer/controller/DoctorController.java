package com.edu.scau.registerconsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.edu.scau.commom.enums.ResponseEnum;
import com.edu.scau.commom.response.ServerResponse;
import com.edu.scau.registerapi.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
@Slf4j
public class DoctorController {
    @Reference
    private DoctorService doctorService;

    @GetMapping("/department/list")
    public ServerResponse getDoctorList(@Param("dpid") Integer dpid,@Param("day")Integer day){
        ServerResponse response = doctorService.getDoctorByDpidAndDay(dpid, day);
        log.info("【获取医生列表】获取成功！结果：{}",response);
        return response;
    }
    @GetMapping("/detail")
    public ServerResponse getDoctorDetail(@Param("doctorId") Integer doctorId){
        if (doctorId == null){
            log.error("【获取医生信息】controller获取前端doctorId失败！");
            return ServerResponse.createByErrorMeeage(ResponseEnum.DOCTOR_ERROR_SEARCH.getDesc());
        }
        ServerResponse response = doctorService.getDoctorDetailById(doctorId);
        log.info("【获取医生信息】获取成功！结果：{}",response);
        return response;
    }

    @PostMapping("/star/list")
    public ServerResponse getStarDoctorList(@Param("openid")String openid){
        if (StringUtils.isEmpty(openid)){
            log.error("【获取收藏医生列表】controller层获取openid为空！");
            return ServerResponse.createByErrorMeeage(ResponseEnum.OPENID_ERROR_CATCH.getDesc());
        }
        ServerResponse response = doctorService.getStarDoctorList(openid);
        log.info("【获取收藏医生列表】获取成功！result={}",response);
        return response;
    }

    @GetMapping("/section/list")
    public ServerResponse getSectionDoctorList(@Param("sectionId")Integer sectionId,@Param("day")Integer day){
        if (StringUtils.isEmpty(sectionId)){
            log.error("【获取科室医生列表】controller层获取sectionId为空！");
            return ServerResponse.createByErrorMeeage(ResponseEnum.DOCTOR_ERROR_SELECT_LIST.getDesc());
        }
        ServerResponse response = doctorService.getSectionDoctorListBySidAndDay(sectionId,day);
        log.info("获取{}科室医生列表成功",sectionId);
        return response;
    }

    @GetMapping("/list")
    public ServerResponse getDoctorList(){
        ServerResponse response = doctorService.getDoctorListAll();
        log.info("【获取所有医生列表】获取成功！结果：{}",response);
        return response;
    }

    @GetMapping("/search")
    public ServerResponse getDoctorListBySearch(@Param("simpleName")String simpleName){
        if (StringUtils.isEmpty(simpleName)){
            log.error("【查询医院列表】查询失败！simpleName为空！");
            return ServerResponse.createByErrorMeeage(ResponseEnum.SEARCH_ERROR_WITH_NULL_INPUT.getDesc());
        }
        ServerResponse response = doctorService.getDoctorListBySearch(simpleName);
        log.info("【查询医院列表】查询成功，关键字：{},结果：{}",simpleName,response);
        return response;
    }
}
