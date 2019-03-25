package com.edu.scau.userprovicer.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.edu.scau.commom.enums.ResponseEnum;
import com.edu.scau.commom.exception.RegisterException;
import com.edu.scau.commom.pojo.StarHospital;
import com.edu.scau.commom.response.ServerResponse;
import com.edu.scau.userapi.service.StarHospitalService;
import com.edu.scau.userprovicer.repository.StarHospitalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

@Slf4j
@Service
public class StarHospitalServiceImpl implements StarHospitalService {
    @Autowired
    private StarHospitalRepository starHospitalRepository;

    @Override
    public List<StarHospital> getStarHospitalIdList(String openid) {
        if (StringUtils.isEmpty(openid)){
            log.error("【获取收藏医院列表】失败，openid为空！");
            throw new RegisterException(ResponseEnum.OPENID_ERROR_CATCH.getDesc());
        }
        List<StarHospital> starHospitalList = starHospitalRepository.selectStarHospitalByOpenid(openid);
        return starHospitalList;
    }

    @Override
    public ServerResponse addStarHospital(String openid, Integer hospitalid) {
        StarHospital starHospital = buildStarHospital(openid, hospitalid);

        Integer result = starHospitalRepository.insertStarHospital(starHospital);
        if (result != 1){
            log.error("【添加收藏医院】更新数据库失败！");
            throw new RegisterException(ResponseEnum.STAR_HOSPITAL_ERROR_INSERT.getDesc());
        }

        return ServerResponse.createBySuccessMessage(ResponseEnum.STAR_HOSPITAL_SUCCESS_INSERT.getDesc());
    }

    private StarHospital buildStarHospital(String openid, Integer hospitalid) {
        StarHospital starHospital = new StarHospital();
        starHospital.setHospitalid(hospitalid);
        starHospital.setOpenid(openid);
        return  starHospital;
    }

    @Override
    public ServerResponse delStarHospital(String openid, Integer hospitalid) {
        Integer result = starHospitalRepository.deleteStarHospitalByIdAndOpenid(openid, hospitalid);
        if (result != 1){
            log.error("【取消收藏医院】更新数据库失败");
            throw new RegisterException(ResponseEnum.STAR_HOSPITAL_ERROR_DELETE.getDesc());
        }
        return ServerResponse.createBySuccessMessage(ResponseEnum.STAR_HOSPITAL_SUCCESS_DELETE.getDesc());
    }

    @Override
    public ServerResponse isStarHospital(Integer hospitalid, String openid){
        StarHospital starHospital = starHospitalRepository.selectByOpenidAndHid(openid,hospitalid);
        if (starHospital == null){
            return ServerResponse.createBySuccessData(false);
        }else {
            return ServerResponse.createBySuccessData(true);
        }
    }
}
