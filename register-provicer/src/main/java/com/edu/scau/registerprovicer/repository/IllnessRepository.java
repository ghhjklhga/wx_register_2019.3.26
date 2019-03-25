package com.edu.scau.registerprovicer.repository;

import com.edu.scau.commom.pojo.Illness;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IllnessRepository {
    //    1、添加
    @Insert("INSERT INTO illness(i_id, i_name, s_id) " +
            "VALUES(#{i.id},#{i.name},#{i.sectionid})")
    Integer insertIllness(@Param("i") Illness illness);

    //    2、删除：按id
    @Delete("DELETE FROM illness " +
            "WHERE i_id = #{id}")
    Integer deleteIllnessById(@Param("id") Integer id);

    //    3、修改
    @Update("")
    Integer updateIllness();

    //    4、查找：按id
    @Select("SELECT i_id as id,i_name as name,s_id as sectionid " +
            "FROM illness " +
            "WHERE i_id = #{id}")
    Illness selectIllnessById(@Param("id") Integer id);

    //    5、查找：按名称
    @Select("SELECT i_id as id,i_name as name,s_id as sectionid " +
            "FROM illness " +
            "WHERE i_name = #{name}")
    List<Illness> selectIllnessByName(@Param("name") String name);

    //    6、查找：按医院
    @Select("SELECT i_id as id,i_name as name,s_id as sectionid " +
            "FROM illness " +
            "WHERE s_id = #{sid}")
    List<Illness> selectIllnessListByHid(@Param("sid") Integer sid);

    //    7、查找：全部
    @Select("SELECT i_id as id,i_name as name,s_id as sectionid " +
            "FROM illness")
    List<Illness> selectIllnessListAll();

    //      8、查找：按sectionid
    @Select("SELECT i_id as id,i_name as name,s_id as sectionid " +
            "FROM illness " +
            "WHERE s_id = #{sid}")
    List<Illness> selectIllnessListBySid(@Param("sid") Integer sid);

}
