package com.gyy.dao;

import com.gyy.domain.Student;

import java.util.List;

public interface StudentMapper {

    /**
     * 需求：查询出所有的学生信息和其对应的老师的信息
     */
    List<Student> findStudents();


}
