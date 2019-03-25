package com.edu.scau.registerprovicer.repository;

import com.edu.scau.commom.pojo.Hospital;
import org.apache.ibatis.annotations.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Mapper
public interface HospitalRepository{
    //1、新增
    @Insert("INSERT INTO hospital(h_id,h_name,h_level,h_address,h_phone,h_description,h_picture,d_id) " +
            "VALUES(#{h.id},#{h.name},#{h.level},#{h.address},#{h.phone},#{h.description},#{h.picture},#{h.distinctid})")
    Integer insertHospital(@Param("h") Hospital hospital);

    //2、删除：按id
    @Delete("DELETE FROM hospital WHERE h_id = #{id}")
    Integer deleteHospital(@Param("id") Integer id);

    //3、更新：名字
    @Update("UPDATE hospital SET h_name=#{name} WHERE h_id=#{id}")
    Integer updateHospital(@Param("id") Integer id,@Param("name") String name);

    //4、查找：按id
    @Select("SELECT h_id as id,h_name as name,h_level as level,h_address as address,h_phone as phone," +
            "h_description as description,h_picture as picture,d_id as distinctid " +
            "FROM hospital " +
            "WHERE h_id = #{id}")
    Hospital selectHospitalById(@Param("id") Integer id);

    //5、查找：按名字
    @Select("SELECT h_id as id,h_name as name,h_level as level,h_address as address,h_phone as phone," +
            "h_description as description,h_picture as picture,d_id as distinctid " +
            "FROM hospital " +
            "WHERE h_name = #{name}")
    Hospital selectHospitalByName(@Param("name") String name);

    //6、查找：按区
    @Select("SELECT h_id as id,h_name as name,h_level as level,h_address as address,h_phone as phone," +
            "h_description as description,h_picture as picture,d_id as distinctid " +
            "FROM hospital " +
            "WHERE d_id = #{distinctid}")
    List<Hospital> selectHospitalListByDid(@Param("distinctid") Integer distinctid);

    //6、查找：市全部
    @Select("SELECT h_id as id,h_name as name,h_level as level,h_address as address,h_phone as phone," +
            "h_description as description,h_picture as picture,d_id as distinctid " +
            "FROM hospital")
    List<Hospital> selectHospitalListAll();

    // 7、模糊查询
    @Select("SELECT h_id as id,h_name as name,h_level as level,h_address as address,h_phone as phone," +
                "h_description as description,h_picture as picture,d_id as distinctid " +
            "FROM hospital " +
            "WHERE h_name LIKE CONCAT('%',#{name},'%')")
    List<Hospital> selectHospitalBySimpleName(@Param("name")String name);
}
