package com.edu.scau.userprovicer.repository;

import com.edu.scau.commom.pojo.DoctorUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DoctorUserRespository {
    @Select("SELECT du_id AS id,du_username AS username,du_password AS password,doc_id AS doctorid " +
            "FROM doctor_user " +
            "WHERE du_username=#{username} AND du_password=#{password}")
    DoctorUser selectDoctorUserByUnameAndPwd(@Param("username")String username, @Param("password")String password);

    @Insert("INSERT INTO doctor_user(du_id,du_username,du_password,doc_id) " +
            "VALUES(#{d.id},#{d.username},#{d.password},#{d.doctorid})")
    Integer insertDoctoruser(@Param("d")DoctorUser doctorUser);

    @Select("SELECT du_id AS id,du_username AS username,du_password AS password,doc_id AS doctorid " +
            "FROM doctor_user " +
            "WHERE du_username=#{username} OR doc_id=#{doctorId}")
    List<DoctorUser> selectDoctorsuserByUsernameOrDoctorId(@Param("username")String username,@Param("doctorId")Integer doctorId);
}
