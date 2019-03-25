package com.edu.scau.registerprovicer.repository;

import com.edu.scau.commom.pojo.Doctor;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DoctorRepository {
    //1、增加
    @Insert("INSERT INTO doctor(doc_id,doc_level,doc_name,doc_price,doc_state,doc_sex,doc_age,dp_id,dp_name,s_id,h_id,h_name,doc_description,doc_picture) " +
            "VALUES(#{d.id},#{d.level},#{d.name},#{d.price},#{d.state},#{d.sex},#{d.age},#{d.departmentid},#{d.departmentname}," +
                    "#{d.sectionid},#{d.hospitalid},#{d.hospitalname},#{d.description},#{d.picture})")
    Integer insertDoctor(@Param("d") Doctor doctor);

    //2、删除
    @Delete("DELETE FROM doctor WHERE doc_id = #{id}")
    Integer deleteDoctorById(@Param("id") Integer id);

    //3、更新：开诊停诊
    @Update("UPDATE doctor SET doc_state=#{state} " +
            "WHERE doc_id = #{doctorId}")
    Integer updateDoctorState(@Param("doctorId") Integer doctorId,@Param("state")Integer state);

    //4、查找：按姓名
    @Select("SELECT doc_id as id,doc_level as level,doc_name as name,doc_sex as sex,doc_age as age,doc_state as state," +
                "dp_id as departmentid,dp_name as departmentname,s_id as sectionid,h_id as hospitalid,doc_price as price," +
                "h_name as hospitalname,doc_description as description,doc_picture as picture " +
            "FROM doctor " +
            "WHERE doc_name = #{name}")
    List<Doctor> selectDoctorByName(@Param("name") String name);

    //5、查找：按医院
    @Select("SELECT doc_id as id,doc_level as level,doc_name as name,doc_sex as sex,doc_age as age,doc_state as state," +
                "dp_id as departmentid,dp_name as departmentname,s_id as sectionid,h_id as hospitalid,doc_price as price," +
                "h_name as hospitalname,doc_description as description,doc_picture as picture " +
            "FROM doctor " +
            "WHERE h_id = #{hid}")
    List<Doctor> selectDoctorListByHid(@Param("hid") Integer hid);

    //6、查找：所有
    @Select("SELECT doc_id as id,doc_level as level,doc_name as name,doc_sex as sex,doc_age as age,doc_state as state," +
                "dp_id as departmentid,dp_name as departmentname,s_id as sectionid,h_id as hospitalid,doc_price as price," +
                "h_name as hospitalname,doc_description as description,doc_picture as picture " +
            "FROM doctor")
    List<Doctor> selectDoctorListAll();

    //7、查找：按id
    @Select("SELECT doc_id as id,doc_level as level,doc_name as name,doc_sex as sex,doc_age as age,doc_state as state," +
                "dp_id as departmentid,dp_name as departmentname,s_id as sectionid,h_id as hospitalid,doc_price as price," +
                "h_name as hospitalname,doc_description as description,doc_picture as picture " +
            "FROM doctor " +
            "WHERE doc_id = #{id}")
    Doctor selectDoctorById(@Param("id") Integer id);

    @Select("SELECT doc_id as id,doc_level as level,doc_name as name,doc_sex as sex,doc_age as age,doc_state as state," +
                "dp_id as departmentid,dp_name as departmentname,s_id as sectionid,h_id as hospitalid,doc_price as price," +
                "h_name as hospitalname,doc_description as description,doc_picture as picture " +
            "FROM doctor " +
            "WHERE s_id = #{sid}")
    List<Doctor> selectDoctorBySid(@Param("sid") Integer sid);

    @Select("SELECT doc_id as id,doc_level as level,doc_name as name,doc_sex as sex,doc_age as age,doc_state as state," +
                "dp_id as departmentid,dp_name as departmentname,s_id as sectionid,h_id as hospitalid,doc_price as price," +
                "h_name as hospitalname,doc_description as description,doc_picture as picture " +
            "FROM doctor " +
            "WHERE dp_id = #{dpid}")
    List<Doctor> selectDoctorByDpid(@Param("dpid") Integer dpid);

    // 查询：模糊查询
    @Select("SELECT doc_id as id,doc_level as level,doc_name as name,doc_sex as sex,doc_age as age,doc_state as state," +
                "dp_id as departmentid,dp_name as departmentname,s_id as sectionid,h_id as hospitalid,doc_price as price," +
                "h_name as hospitalname,doc_description as description,doc_picture as picture " +
            "FROM doctor " +
            "WHERE doc_name LIKE CONCAT('%',#{name},'%')")
    List<Doctor> selectDoctorListBySimpleName(@Param("name")String name);

}
