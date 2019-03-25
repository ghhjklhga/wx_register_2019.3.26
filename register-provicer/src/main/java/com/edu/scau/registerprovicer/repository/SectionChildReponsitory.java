package com.edu.scau.registerprovicer.repository;

import com.edu.scau.commom.pojo.SectionChild;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SectionChildReponsitory {
    @Select("SELECT sc_id AS id,sc_name AS name,s_id AS sectionid " +
            "FROM section_child " +
            "WHERE s_id = #{id}")
    List<SectionChild> selectSectionChildListBySid(@Param("id") Integer id);
}
