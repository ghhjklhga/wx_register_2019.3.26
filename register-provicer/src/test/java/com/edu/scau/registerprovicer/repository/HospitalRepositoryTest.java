package com.edu.scau.registerprovicer.repository;

import com.edu.scau.commom.enums.HospitalEnum;
import com.edu.scau.commom.pojo.Hospital;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HospitalRepositoryTest {
    @Autowired
    HospitalRepository hospitalRepository;

    @Test
    public void insertHospital() {
        Hospital hospital = new Hospital();
        hospital.setName("沿江门诊医院");
        hospital.setAddress("沿江路");
        hospital.setPhone("0766-2933212");
        hospital.setDescription("妇科圣手");
        hospital.setLevel(HospitalEnum.THIRD_GRADE.getCode());
        hospital.setPicture("xxx.com/xxx");

        Integer id = hospitalRepository.insertHospital(hospital);
        System.out.println(id);
    }

    @Test
    public void deleteHospital() {
        System.out.println(hospitalRepository.deleteHospital(2));
    }

    @Test
    public void updateHospital() {
        System.out.println(hospitalRepository.updateHospital(1, "人民医院"));
    }

    @Test
    public void selectHospitalById() {
        System.out.println(hospitalRepository.selectHospitalById(1).getName());
    }

    @Test
    public void selectHospitalByName() {
        System.out.println(hospitalRepository.selectHospitalByName("人民医院").getName());
    }

    @Test
    public void selectHospitalListByDid() {
        System.out.println(hospitalRepository.selectHospitalListByDid(1).get(0).getName());
    }

    @Test
    public void selectAll(){
        System.out.println(hospitalRepository.selectHospitalListAll().get(1).getName());
    }
}