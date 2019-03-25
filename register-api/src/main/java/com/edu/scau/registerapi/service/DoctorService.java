package com.edu.scau.registerapi.service;

import com.edu.scau.commom.pojo.Doctor;
import com.edu.scau.commom.response.ServerResponse;

import java.util.List;

public interface DoctorService {
    ServerResponse addDoctor(Doctor doctor);

    ServerResponse getDoctorListByHid(Integer hospitalid);

    ServerResponse getDoctorListAll();

    ServerResponse getDoctorDetailById(Integer doctorid);

    ServerResponse getDoctorBySid(Integer sectionid);

    ServerResponse getDoctorByDpidAndDay(Integer dpid, Integer day);

    ServerResponse getSectionDoctorListBySidAndDay(Integer sid, Integer day);

    //模糊查询
    ServerResponse getDoctorListBySearch(String name);

    //  供其他provider调用
    Doctor getDoctorById(Integer id);

    ServerResponse getStarDoctorList(String openid);

}
