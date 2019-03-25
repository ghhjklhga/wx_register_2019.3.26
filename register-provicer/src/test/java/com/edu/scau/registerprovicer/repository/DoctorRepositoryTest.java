package com.edu.scau.registerprovicer.repository;

import com.edu.scau.commom.enums.DoctorEnum;
import com.edu.scau.commom.pojo.Doctor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.print.Doc;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DoctorRepositoryTest {
    @Autowired
    DoctorRepository doctorRepository;

    @Test
    public void insertDoctor() {
        Doctor doctor = new Doctor();
        //doctor.setId(3412);
        doctor.setName("假牙");
        doctor.setLevel(DoctorEnum.TECHNICIAN.getCode());
        doctor.setAge(12);
        doctor.setSex("男");
        doctor.setDepartmentid(2);
        doctor.setDepartmentname("内科门诊");
        doctor.setDescription("妇科圣手");
        doctor.setSectionid(1);
        doctor.setHospitalid(1);
        doctor.setHospitalname("沿江门诊医院");
        doctor.setPicture("www.xxx.com/xxx");

        System.out.println(doctorRepository.insertDoctor(doctor));
    }

    @Test
    public void deleteDoctor() {
        System.out.println(doctorRepository.deleteDoctorById(1));
    }

    @Test
    public void updateDoctor() {
    }

    @Test
    public void selectDoctorByName() {
        System.out.println(doctorRepository.selectDoctorByName("假牙").get(0).getDescription());
    }

    @Test
    public void selectDoctorListByHid() {
        System.out.println(doctorRepository.selectDoctorListByHid(1).get(0).getDescription());
    }

    @Test
    public void selectDoctorListAll() {
        System.out.println(doctorRepository.selectDoctorListAll().get(0).getName());
    }

    @Test
    public void selectDoctorByDpId() {
        System.out.println(doctorRepository.selectDoctorByDpid(7));
    }
}