package com.edu.scau.registerprovicer.serviceImpl;

import com.edu.scau.commom.response.ServerResponse;
import com.edu.scau.commom.vo.HospitalVO;
import com.edu.scau.registerapi.service.HospitalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class HospitalServiceImplTest {
    @Autowired
    private HospitalService hospitalService;

    @Test
    public void addHospital() {
    }

    @Test
    public void getHospitalListByDistinctId() {
    }

    @Test
    public void getHospitalListAll() {
        ServerResponse serverResponse = hospitalService.getHospitalListAll();
        List<HospitalVO> hospitalVOList = (List<HospitalVO>) serverResponse.getData();
        log.info("{}",hospitalVOList);
    }
}