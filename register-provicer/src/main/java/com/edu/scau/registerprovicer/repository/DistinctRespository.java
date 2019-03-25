package com.edu.scau.registerprovicer.repository;

import com.edu.scau.commom.pojo.Distinct;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DistinctRespository {
    //    1、添加
    @Insert("")
    Integer insertDistinct(Distinct distinct);

    //    2、查找
    @Select("SELECT d_id AS id,d_name AS name " +
            "FROM r_distinct")
    List<Distinct> selectDistinctAll();
}
