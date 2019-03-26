package com.edu.scau.registerapi.service;

import com.edu.scau.commom.form.NumberForm;
import com.edu.scau.commom.pojo.Number;
import com.edu.scau.commom.response.ServerResponse;

public interface NumberService {

    ServerResponse getNumberByDidAndDay(Integer doctorid, Integer day);

    ServerResponse addNumberListByDoctorid(NumberForm numberForm);

    ServerResponse getNumberById(Integer numberId);

    ServerResponse getNumberByDoctorid(Integer doctorId);

    //  扣库存
    boolean decNumberById(Integer numberId);

    //  供其他provider调用
    Number getNumberInfoById(Integer id);
}
