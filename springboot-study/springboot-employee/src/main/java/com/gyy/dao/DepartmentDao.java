package com.gyy.dao;

import com.gyy.pojo.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 模拟数据库中数据
 */
@Repository
public class DepartmentDao {
    //根据部门id获得department
    private static Map<Integer, Department> departmentMap;

    static {
        departmentMap = new ConcurrentHashMap<>();
        departmentMap.put(101, new Department(101, "教学部"));
        departmentMap.put(102, new Department(102, "后勤部"));
        departmentMap.put(103, new Department(103, "研发部"));
        departmentMap.put(104, new Department(104, "人事部"));
        departmentMap.put(105, new Department(105, "运维部"));
    }

    //获得所有部门信息
    public Collection<Department> getDepartment(){
        return departmentMap.values();
    }

    //通过id获得对应的部门
    public Department getDepartmentById(Integer id){
        return departmentMap.get(id);
    }
}

