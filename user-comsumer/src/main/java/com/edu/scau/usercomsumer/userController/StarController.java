package com.edu.scau.usercomsumer.userController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.edu.scau.commom.enums.ResponseEnum;
import com.edu.scau.commom.response.ServerResponse;
import com.edu.scau.userapi.service.StarDoctorService;
import com.edu.scau.userapi.service.StarHospitalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/star")
@Slf4j
public class StarController {
    @Reference
    private StarHospitalService starHospitalService;
    @Reference
    private StarDoctorService starDoctorService;

    @PostMapping("/hospital/add")
    public ServerResponse addHospitalStar(@RequestParam("openid") String openid, @RequestParam("hospitalid")Integer hospitalid){

        if (StringUtils.isEmpty(openid)){
            log.error("【添加收藏医院】controller层获取openid为空！");
            return ServerResponse.createByErrorMeeage(ResponseEnum.OPENID_ERROR_CATCH.getDesc());
        }
        if (StringUtils.isEmpty(hospitalid)){
            log.error("【添加收藏医院】controller层获取hospitalid为空！");
            return ServerResponse.createByErrorMeeage(ResponseEnum.STAR_HOSPITAL_ERROR_INSERT.getDesc());
        }
        ServerResponse response = starHospitalService.addStarHospital(openid, hospitalid);
        log.info("【添加收藏医院】成功！");
        return response;
    }

    @PostMapping("/hospital/cancel")
    public ServerResponse cancelHospitalStar(@RequestParam("openid") String openid, @RequestParam("hospitalid")Integer hospitalid){
        // TODO: 2019/3/2
        log.info("openid={},hospitalid={}",openid,hospitalid);

        if (StringUtils.isEmpty(openid)){
            log.error("【取消收藏医院】controller层获取openid为空！");
            return ServerResponse.createByErrorMeeage(ResponseEnum.OPENID_ERROR_CATCH.getDesc());
        }
        if (StringUtils.isEmpty(hospitalid)){
            log.error("【取消收藏医院】controller层获取hospitalid为空！");
            return ServerResponse.createByErrorMeeage(ResponseEnum.STAR_HOSPITAL_ERROR_DELETE.getDesc());
        }
        ServerResponse response = starHospitalService.delStarHospital(openid, hospitalid);
        log.info("【取消收藏医院】成功！");
        return response;
    }

    @PostMapping("/hospital/state")
    public ServerResponse getHospitalStarState(@RequestParam("openid") String openid, @RequestParam("hospitalid")Integer hospitalid){
        ServerResponse response = starHospitalService.isStarHospital(hospitalid,openid);
        log.info("【是否收藏医院】结果：{}",response);
        return response;
    }

    @PostMapping("/doctor/add")
    public ServerResponse addDoctorStar(@RequestParam("openid")String openid, @RequestParam("doctorid")Integer doctorid){
        // TODO: 2019/3/2
        log.info("openid={},doctor={}",openid,doctorid);

        if (StringUtils.isEmpty(openid)){
            log.error("【取消收藏医院】controller层获取openid为空！");
            return ServerResponse.createByErrorMeeage(ResponseEnum.OPENID_ERROR_CATCH.getDesc());
        }
        if (StringUtils.isEmpty(doctorid)){
            log.error("【取消收藏医院】controller层获取hospitalid为空！");
            return ServerResponse.createByErrorMeeage(ResponseEnum.STAR_DOCTOR_ERROR_DELETE.getDesc());
        }
        ServerResponse response = starDoctorService.addStarDoctor(openid, doctorid);
        log.info("【添加收藏医生】成功！");
        return response;
    }

    @PostMapping("/doctor/cancel")
    public ServerResponse cancelDoctorStar(@RequestParam("openid")String openid, @RequestParam("doctorid")Integer doctorid){
        // TODO: 2019/3/2
        log.info("openid={},doctor={}",openid,doctorid);

        if (StringUtils.isEmpty(openid)){
            log.error("【取消收藏医生】controller层获取openid为空！");
            return ServerResponse.createByErrorMeeage(ResponseEnum.OPENID_ERROR_CATCH.getDesc());
        }
        if (StringUtils.isEmpty(doctorid)){
            log.error("【取消收藏医生】controller层获取hospitalid为空！");
            return ServerResponse.createByErrorMeeage(ResponseEnum.STAR_DOCTOR_ERROR_DELETE.getDesc());
        }
        ServerResponse response = starDoctorService.delStarDoctor(openid, doctorid);
        log.info("【取消收藏医生】成功！");
        return response;
    }

    @PostMapping("/doctor/state")
    public ServerResponse getDoctorStarState(@RequestParam("doctorid")Integer doctorid,@RequestParam("openid")String openid){
        ServerResponse response = starDoctorService.isStarDoctor(openid,doctorid);
        log.info("【是否收藏医生】结果：{}",response);
        return response;
    }
}
