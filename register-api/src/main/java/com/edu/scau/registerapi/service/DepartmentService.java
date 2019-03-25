package com.edu.scau.registerapi.service;

import com.edu.scau.commom.pojo.Department;
import com.edu.scau.commom.response.ServerResponse;

public interface DepartmentService {

    ServerResponse addDepartment(Department department);

    ServerResponse getDepartmentlistByHid(Integer hospitalid);

    //  获取：通过Id
    Department getDepartmentById(Integer departmentId);
}
