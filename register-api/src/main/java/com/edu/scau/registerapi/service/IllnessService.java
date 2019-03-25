package com.edu.scau.registerapi.service;

import com.edu.scau.commom.pojo.Illness;
import com.edu.scau.commom.response.ServerResponse;

public interface IllnessService {
    ServerResponse addIllness(Illness illness);

    ServerResponse delIllness(Integer id);

    ServerResponse getIllnessAllListBySection();

    ServerResponse getIllnessByName(String name);

}
