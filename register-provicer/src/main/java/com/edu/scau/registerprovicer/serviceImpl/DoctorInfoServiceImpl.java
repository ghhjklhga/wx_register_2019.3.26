package com.edu.scau.registerprovicer.serviceImpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.edu.scau.api.redisapi.api.RedisService;
import com.edu.scau.commom.enums.DoctorStateEnum;
import com.edu.scau.commom.enums.ResponseEnum;
import com.edu.scau.commom.pojo.DoctorUser;
import com.edu.scau.commom.response.ServerResponse;
import com.edu.scau.noticeapi.service.NoticeService;
import com.edu.scau.orderapi.service.OrderService;
import com.edu.scau.registerapi.service.DoctorInfoService;
import com.edu.scau.registerprovicer.repository.DoctorRepository;
import com.edu.scau.registerprovicer.repository.NumberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class DoctorInfoServiceImpl implements DoctorInfoService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private NumberRepository numberRepository;
    @Reference
    private OrderService orderService;
    @Reference
    private RedisService redisService;
    @Reference
    private NoticeService noticeService;

    //停诊
    @Override
    @Transactional
    public ServerResponse closeDoctor(String token, Integer visitdate, String title, String context) {
        DoctorUser doctorUser = (DoctorUser) redisService.get(token);
        //  1、修改状态
        Integer result = doctorRepository.updateDoctorState(doctorUser.getDoctorid(),DoctorStateEnum.STOP.getCode());
        if (result != 1){
            log.error("【医生停诊】修改医生状态失败！result:{}",result);
            return ServerResponse.createByErrorMeeage(ResponseEnum.DOCTOR_CLOSE_ERROR.getDesc());
        }
        // 2、更改订单状态
        ServerResponse response = orderService.cancelOrderByDidAndTime(doctorUser.getDoctorid(),visitdate, 100);
        // 3、发放通知
        if(noticeService.addNotice(title, context) == false){
            return ServerResponse.createByErrorMeeage(ResponseEnum.DOCTOR_CLOSE_ERROR.getDesc());
        }
        return response;
    }
    //开诊
    @Override
    public ServerResponse openDoctorByDid(String token) {
        DoctorUser doctorId = (DoctorUser) redisService.get(token);
        Integer result = doctorRepository.updateDoctorState(doctorId.getDoctorid(),DoctorStateEnum.NORMAL.getCode());
        if (result != 1){
            log.error("【医生开诊】修改医生状态失败！result：{}",result);
            return ServerResponse.createByErrorMeeage(ResponseEnum.DOCTOR_OPEN_ERROR.getDesc());
        }
        log.info("【医生开诊】成功！result：{}",result);
        return ServerResponse.createBySuccessMessage(ResponseEnum.DOCTOR_CLOSE_SUCCESS.getDesc());
    }

}
