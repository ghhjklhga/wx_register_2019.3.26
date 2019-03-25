package com.edu.scau.registerprovicer.repository;

import com.edu.scau.commom.pojo.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DepartmentRepository {
    //    1、添加
    @Insert("INSERT INTO department(dp_id,dp_name,h_id) " +
            "VALUES(#{dp.id},#{dp.name},#{dp.hospitalid})")
    Integer insertDepartment(@Param("dp") Department department);

    //    2、删除：按id
    @Delete("DELETE FROM department " +
            "WHERE dp_id = #{id}")
    Integer deleteDepartment(@Param("id") Integer id);

    //    3、修改
    @Update("UPDATE department SET")
    Integer updateDepartment(@Param("dp") Department department);

    //    4、查找：按医院id
    @Select("SELECT dp_id as id,dp_name as name,h_id as hospitalid " +
            "FROM department " +
            "WHERE h_id = #{hid}")
    List<Department> selectDepartmentListByHid(@Param("hid") Integer hid);

    //      5、查找：按id
    @Select("SELECT dp_id as id,dp_name as name,h_id as hospitalid " +
            "FROM department " +
            "WHERE dp_id = #{id}")
    Department selectDepartmentById(@Param("id") Integer id);


}
