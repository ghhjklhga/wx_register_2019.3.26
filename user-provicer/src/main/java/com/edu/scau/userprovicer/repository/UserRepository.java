package com.edu.scau.userprovicer.repository;

import com.edu.scau.commom.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.util.List;

@Mapper
public interface UserRepository {
    //创建用户
    @Insert("INSERT INTO user(u_id,u_username,u_password,u_phone,u_idnumber,u_address,u_openid) " +
            "VALUE(#{u.id},#{u.username},#{u.password},#{u.phone},#{u.idnumber},#{u.address},#{u.openid})")
    Integer insertUser(@Param("u")User user);

    //更新用户信息
    @Update("UPDATE SET " +
            "WHERE u_openid = #{openid}")
    Integer updateUserByOpenid(@Param("openid") String openid);

    //查找用户信息
    @Select("SELECT u_id as id,u_username as username,u_password as password,u_phone as phone," +
                "u_idnumber as idnumber,u_address as address,u_openid as openid " +
            "FROM user " +
            "WHERE u_openid = #{openid}")
    User selectUserByOpenid(@Param("openid") String openid);

    //查找： 所有
    @Select("SELECT u_id as id,u_username as username,u_password as password,u_phone as phone," +
                "u_idnumber as idnumber,u_address as address,u_openid as openid " +
            "FROM user")
    List<User> selectUserAll();

}
