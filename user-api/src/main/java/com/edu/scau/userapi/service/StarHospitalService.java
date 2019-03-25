package com.edu.scau.userapi.service;

import com.edu.scau.commom.pojo.StarHospital;
import com.edu.scau.commom.response.ServerResponse;

import java.util.List;

public interface StarHospitalService {

    List<StarHospital> getStarHospitalIdList(String openid);

    ServerResponse addStarHospital(String openid, Integer hospitalid);

    ServerResponse delStarHospital(String openid, Integer hospitalid);

    ServerResponse isStarHospital(Integer hospital, String openid);
}
