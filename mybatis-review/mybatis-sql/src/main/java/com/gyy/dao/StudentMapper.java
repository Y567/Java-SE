package com.gyy.dao;

import com.gyy.domain.Student;

import java.util.List;
import java.util.Map;

public interface StudentMapper {

    /**
     * 测试一下动态sql（IF的使用）
     * @param map 查询条件
     * @return    返回查询的结果
     */
    List<Student> getStudentBySQL(Map<String,String> map);

    /**
     * 测试一下choose标签
     * 实现选择其中的一个查询条件进行查询
     */
    List<Student> getStudentByChoose(Map<String,String> map);

    /**
     * 更新数据
     */
    int updateStudentById(Map<String,String> map);


    /**
     * foreach标签的入门
     */
    List<Student> getStudentByForeach(Map<String,Object> map);


}
