package com.edu.scau.registerapi.service;

import com.edu.scau.commom.form.NoticeForm;
import com.edu.scau.commom.response.ServerResponse;
import org.springframework.transaction.annotation.Transactional;

public interface DoctorInfoService {
    //停诊
    @Transactional
    ServerResponse closeDoctor(String token, Integer visitdate, String title, String context);

    ServerResponse openDoctorByDid(String token);

}
