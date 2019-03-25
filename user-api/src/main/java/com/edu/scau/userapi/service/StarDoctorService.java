package com.edu.scau.userapi.service;

import com.edu.scau.commom.pojo.StarDoctor;
import com.edu.scau.commom.response.ServerResponse;

import java.util.List;

public interface StarDoctorService {

    List<StarDoctor> getStarDoctorList(String openid);

    ServerResponse addStarDoctor(String openid, Integer doctorid);

    ServerResponse delStarDoctor(String openid, Integer doctorid);

    ServerResponse isStarDoctor(String openid, Integer doctorid);
}
