package com.gyy.dao;

import com.gyy.domain.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TeacherMapper {

    /**
     * 根据id查老师
     * @param id id
     * @return  返回一个老师对象
     */
    @Select("select * from teacher where id = #{id}")
    Teacher findTeacherById(@Param("id") int id);

}
