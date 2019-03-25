package com.edu.scau.commom.vo;

import com.edu.scau.commom.pojo.DepartmentChild;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DepartmentVO implements Serializable {
    private Integer dept_id;
    private String dept_name;
    private List<DepartmentChildVO> departmentChildList;

    public DepartmentVO(Integer dept_id, String dept_name) {
        this.dept_id = dept_id;
        this.dept_name = dept_name;
    }
}
