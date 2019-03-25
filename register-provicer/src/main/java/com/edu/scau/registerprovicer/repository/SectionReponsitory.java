package com.edu.scau.registerprovicer.repository;

import com.edu.scau.commom.pojo.Section;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SectionReponsitory {
    @Select("SELECT s_id AS id,s_name AS name " +
            "FROM section")
    List<Section> selectSectionListAll();
}
