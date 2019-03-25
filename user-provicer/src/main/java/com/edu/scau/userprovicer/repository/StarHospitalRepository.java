package com.edu.scau.userprovicer.repository;

import com.edu.scau.commom.pojo.StarHospital;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StarHospitalRepository {
    @Select("SELECT st_h_id AS id,u_id AS userid,u_openid AS openid,h_id AS hospitalid " +
            "FROM star_hospital " +
            "WHERE u_openid = #{openid}")
    List<StarHospital> selectStarHospitalByOpenid(@Param("openid") String openid);

    @Insert("INSERT INTO star_hospital(st_h_id,u_id,u_openid,h_id) " +
            "VALUES(#{s.id},#{s.userid},#{s.openid},#{s.hospitalid})")
    Integer insertStarHospital(@Param("s") StarHospital star);

    @Delete("DELETE " +
            "FROM star_hospital " +
            "WHERE u_openid = #{openid} AND h_id = #{hospitalid}")
    Integer deleteStarHospitalByIdAndOpenid(@Param("openid")String openid,@Param("hospitalid")Integer hospitalid);

    @Select("SELECT st_h_id AS id,u_id AS userid,u_openid AS openid,h_id AS hospitalid " +
            "FROM star_hospital " +
            "WHERE u_openid = #{openid} AND h_id=#{hospitalid}")
    StarHospital selectByOpenidAndHid(@Param("openid")String openid,@Param("hospitalid")Integer hospitalid);
}
