package com.edu.scau.registerprovicer.repository;

import com.edu.scau.commom.pojo.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DepartmentRepositoryTest {
    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    public void insertDepartment() {
        Department department = new Department();
        department.setName("内科科门诊");
        department.setHospitalid(2);

        System.out.println(departmentRepository.insertDepartment(department));
    }

    @Test
    public void deleteDepartment() {
        System.out.println(departmentRepository.deleteDepartment(1));
    }

    @Test
    public void updateDepartment() {
    }

    @Test
    public void selectDepartment() {
        System.out.println(departmentRepository.selectDepartmentListByHid(2).get(0).getName());
    }
}