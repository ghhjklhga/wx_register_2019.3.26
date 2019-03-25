package com.edu.scau.noticeprovicer.repository;


import com.edu.scau.commom.pojo.Notice;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoticeRepository {

    @Select("SELECT n_id as id,n_title as title,n_context as context,n_time as time " +
                "FROM notice WHERE n_id = #{id}")
    Notice selectNoticeById(@Param("id") int id);

    @Insert("INSERT INTO notice(n_id, n_title, n_context, n_time) " +
                "VALUES(#{n.id}, #{n.title}, #{n.context}, #{n.time})")
    Integer insertNotice(@Param("n") Notice notice);

//    @Results({
//            @Result(property = "id", column = "n_id"),
//            @Result(property = "title", column = "n_title"),
//            @Result(property = "context", column = "n_context"),
//            @Result(property = "time", column = "n_time")
//    })
    @Select("SELECT n_id as id,n_title as title,n_context as context,n_time as time FROM notice")
    List<Notice> listNotice();

}
