package com.edu.scau.userprovicer.repository;

import com.edu.scau.commom.pojo.StarDoctor;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StarDoctorRespository {
    @Select("SELECT st_d_id AS id,u_id AS userid,u_openid AS openid,doc_id AS doctorid " +
            "FROM star_doctor " +
            "WHERE u_openid = #{openid}")
    List<StarDoctor> selectStarDoctorListByOpenid(@Param("openid") String openid);

    @Insert("INSERT INTO star_doctor(st_d_id,u_id,u_openid,doc_id) " +
            "VALUES(#{s.id},#{s.userid},#{s.openid},#{s.doctorid})")
    Integer insertStarDoctor(@Param("s") StarDoctor starDoctor);

    @Delete("DELETE " +
            "FROM star_doctor " +
            "WHERE u_openid = #{openid} AND doc_id = #{doctorid}")
    Integer deleteStarDoctorByIdAndOpenid(@Param("openid")String openid,@Param("doctorid")Integer doctorid);

    @Select("SELECT st_d_id AS id,u_id AS userid,u_openid AS openid,doc_id AS doctorid " +
            "FROM star_doctor " +
            "WHERE u_openid = #{openid} AND doc_id=#{doctorid}")
    StarDoctor selectByOpenidAndDid(@Param("openid") String openid,@Param("doctorid")Integer doctorid);
}
