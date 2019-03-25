package com.edu.scau.registerprovicer.repository;

import com.edu.scau.commom.pojo.DepartmentChild;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DepartmentChildRepository {
    @Select("SELECT dept_child_id AS id,dept_child_name AS name " +
            "FROM dept_child " +
            "WHERE dept_id = #{deptId}")
    List<DepartmentChild> selectChildByDeptId(@Param("deptId") Integer deptId);
}
