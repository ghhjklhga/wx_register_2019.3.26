package com.edu.scau.noticeprovicer.repository;

import com.edu.scau.commom.pojo.Feedback;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface FeedbackRepository {
    @Insert("INSERT INTO feedback(f_id, f_title, f_context, f_contact, f_time, u_userid) " +
                "VALUES(#{id},#{title},#{context},#{contact},#{time},#{userid})")
    Integer insertFeedback(Feedback feedback);

    @Select("SELECT f_id as id,f_title as title,f_context as context,f_contact as contact,f_time as time,u_userid as userid " +
                "FROM feedback WHERE f_id = #{id}")
    Feedback selectFeedbackById(@Param("id") int id);

    @Results({
            @Result(property = "id", column = "f_id"),
            @Result(property = "title", column = "f_title"),
            @Result(property = "context", column = "f_context"),
            @Result(property = "contact", column = "f_context"),
            @Result(property = "time", column = "f_time"),
            @Result(property = "userid", column = "u_userid")
    })
    @Select("SELECT f_id,f_title,f_context,f_time,u_userid FROM feedback")
    List<Feedback> listFeedback();
}
