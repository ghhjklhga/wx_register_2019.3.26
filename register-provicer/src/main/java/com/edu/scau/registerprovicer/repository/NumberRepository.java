package com.edu.scau.registerprovicer.repository;

import org.apache.ibatis.annotations.*;
import com.edu.scau.commom.pojo.Number;

import java.util.Date;
import java.util.List;

@Mapper
public interface NumberRepository {
    //    1、添加
    @Insert("INSERT INTO number(n_id,n_sum,n_rest,n_date,n_time,doc_id) " +
            "VALUES(#{n.id},#{n.sum},#{n.rest},#{n.date},#{n.time},#{n.doctorid})")
    Integer insertNumber(@Param("n") Number number);

    //    2、删除
    @Delete("DELETE FROM number " +
            "WHERE n_time <= #{time}")
    Integer deleteNumberByTime(@Param("time") Date time);

    //    3、查找：按医生
    @Select("SELECT n_id as id,n_sum as sum,n_rest as rest,n_date as date,n_time as time,doc_id as doctorid " +
            "FROM number " +
            "WHERE doc_id = #{did}")
    List<Number> selectNumberByDoctorid(@Param("did") Integer did);

    //      查找：按id
    @Select("SELECT n_id as id,n_sum as sum,n_rest as rest,n_date as date,n_time as time,doc_id as doctorid " +
            "FROM number " +
            "WHERE n_id = #{id}")
    Number selectNumberById(@Param("id") Integer id);

    //    4、更新：扣库存
    @Update("UPDATE number SET n_rest = n_rest-1 " +
            "WHERE n_id = #{id}")
    Integer decNumberById(@Param("id") Integer id);

    //5、更新：停诊
    @Update("UPDATE number SET n_state = 1 " +
            "WHERE n_id = #{nid} AND doc_id = #{did}")
    Integer close(@Param("nid") Integer nid, @Param("did") Integer did);

    //6 、查找：按医生id和星期几
    @Select("SELECT n_id as id,n_sum as sum,n_rest as rest,n_date as date,n_time as time,doc_id as doctorid " +
            "FROM number " +
            "WHERE doc_id = #{did} AND n_date = #{date}")
    List<Number> selectNumberListByDidAndDate(@Param("did") Integer did,@Param("date")Integer date);

    @Select("SELECT n_id as id,n_sum as sum,n_rest as rest,n_date as date,n_time as time,doc_id as doctorid " +
            "FROM number " +
            "WHERE doc_id = #{did} AND n_date = #{day}")
    List<Number> selectNumberByDidAndDay(@Param("did") Integer did,@Param("day") Integer day);
}
