package com.edu.scau.userprovicer.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.edu.scau.commom.enums.ResponseEnum;
import com.edu.scau.commom.exception.RegisterException;
import com.edu.scau.commom.pojo.StarDoctor;
import com.edu.scau.commom.response.ServerResponse;
import com.edu.scau.userapi.service.StarDoctorService;
import com.edu.scau.userprovicer.repository.StarDoctorRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
public class StarDoctorServiceImpl implements StarDoctorService {
    @Autowired
    private StarDoctorRespository starDoctorRespository;

    @Override
    public List<StarDoctor> getStarDoctorList(String openid) {
        if(StringUtils.isEmpty(openid)){
            log.error("【获取收藏医生列表】获取失败！openid为空");
            throw new RegisterException(ResponseEnum.OPENID_ERROR_CATCH.getDesc());
        }
        List<StarDoctor> starDoctorList = starDoctorRespository.selectStarDoctorListByOpenid(openid);
        return starDoctorList;
    }

    @Override
    public ServerResponse addStarDoctor(String openid, Integer doctorid) {
        StarDoctor starDoctor = buildDoctor(openid, doctorid);

        Integer result = starDoctorRespository.insertStarDoctor(starDoctor);
        if(result != 1){
            log.error("【添加收藏医生】更新数据库失败！");
            throw new RegisterException(ResponseEnum.STAR_DOCTOR_ERROR_INSERT.getDesc());
        }
        return ServerResponse.createBySuccessMessage(ResponseEnum.STAR_DOCTOR_SUCCESS_INSERT.getDesc());
    }
    private StarDoctor buildDoctor(String openid, Integer doctorid){
        StarDoctor starDoctor = new StarDoctor();
        starDoctor.setDoctorid(doctorid);
        starDoctor.setOpenid(openid);

        return starDoctor;
    }

    @Override
    public ServerResponse delStarDoctor(String openid, Integer doctorid) {
        
        Integer result = starDoctorRespository.deleteStarDoctorByIdAndOpenid(openid, doctorid);
        if(result != 1){
            log.error("【取消收藏医生】更新数据库失败！");
            throw new RegisterException(ResponseEnum.STAR_DOCTOR_ERROR_DELETE.getDesc());
        }
        return ServerResponse.createBySuccessMessage(ResponseEnum.STAR_DOCTOR_SUCCESS_DELETE.getDesc());
    }

    @Override
    public ServerResponse isStarDoctor(String openid,Integer doctorid){
        StarDoctor starDoctor = starDoctorRespository.selectByOpenidAndDid(openid,doctorid);
        if (starDoctor == null){
            return ServerResponse.createBySuccessData(false);
        }else {
            return ServerResponse.createBySuccessData(true);
        }
    }
}
