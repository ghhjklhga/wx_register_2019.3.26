package com.edu.scau.registerconsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.edu.scau.commom.enums.ResponseEnum;
import com.edu.scau.commom.response.ServerResponse;
import com.edu.scau.registerapi.service.HospitalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hospital")
@Slf4j
public class HospitalController {
    @Reference
    private HospitalService hospitalService;

    @GetMapping("/distinct/list")
    public ServerResponse getHospital(@Param("distinctId") Integer distinctId){
        if(distinctId == null){
            log.error("【获取医院列表】获取前端参数失败！");
            return ServerResponse.createBySuccessMessage(ResponseEnum.HOSPITAL_LIST_ERROR_SELECT.getDesc());
        }
        if (distinctId == 0){
            log.info("【获取医院列表】获取广州市医院列表成功！");
            return hospitalService.getHospitalListAll();
        }else {
            log.info("【获取医院列表】获取{}区列表成功！",distinctId);
            return hospitalService.getHospitalListByDistinctId(distinctId);
        }
    }

    @GetMapping("/detail")
    public ServerResponse getHospitalDetail(@Param("hid") Integer hid){
        ServerResponse response = hospitalService.getHospitalDetailById(hid);
        log.info("【获取医院详情】获取成功！结果：{}",response);
        return response;
    }

    @PostMapping("/star/list")
    public ServerResponse getStarHospitalList(@Param("openid")String openid){
        if (StringUtils.isEmpty(openid)){
            log.error("【获取收藏医院列表】controller层获取openid为空！");
            return ServerResponse.createByErrorMeeage(ResponseEnum.OPENID_ERROR_CATCH.getDesc());
        }
        ServerResponse response = hospitalService.getStarHospitalList(openid);
        log.info("【获取收藏医院列表】获取成功!");
        return response;
    }

    @GetMapping("/list")
    public ServerResponse getHospitalList(){
        ServerResponse response = hospitalService.getHospitalListAll();
        log.info("【获取所有医院列表】获取成功！结果：{}",response);
        return response;
    }

    @GetMapping("/search")
    public ServerResponse getHospitalListBySearch(@Param("simpleName")String simpleName){
        if (StringUtils.isEmpty(simpleName)){
            log.error("【查询医院列表】查询失败！simpleName为空！");
            return ServerResponse.createByErrorMeeage(ResponseEnum.SEARCH_ERROR_WITH_NULL_INPUT.getDesc());
        }
        ServerResponse response = hospitalService.getHospitalListBySimplename(simpleName);
        log.info("【查询医院列表】查询成功！关键字：{}，结果: {}",simpleName,response);
        return response;
    }
}
