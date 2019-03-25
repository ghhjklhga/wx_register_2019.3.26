package com.edu.scau.registerapi.service;

import com.edu.scau.commom.pojo.Hospital;
import com.edu.scau.commom.response.ServerResponse;

public interface HospitalService {

    ServerResponse addHospital(Hospital hospital);

    ServerResponse getHospitalListByDistinctId(Integer distinctid);

    ServerResponse getHospitalListAll();

    ServerResponse getHospitalDetailById(Integer id);

    //模糊查询
    ServerResponse getHospitalListBySimplename(String simpleName);

    //  供其他proider调用
    Hospital getHospitalById(Integer id);

    ServerResponse getStarHospitalList(String openid);
}
