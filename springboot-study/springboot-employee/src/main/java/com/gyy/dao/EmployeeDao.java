package com.gyy.dao;

import com.gyy.pojo.Department;
import com.gyy.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 员工表
 */
@Repository
public class EmployeeDao {
    //key为员工id
    private static Map<Integer, Employee> employeeMap;
    @Autowired
    private DepartmentDao departmentDao;
    static{
        employeeMap = new ConcurrentHashMap<>();
        employeeMap.put(1001,new Employee(1001,"阿狗1号","33451121qq.com",1,new Department(101,"教学部")));
        employeeMap.put(1002,new Employee(1002,"阿狗2号","33451122qq.com",0,new Department(101,"教学部")));
        employeeMap.put(1003,new Employee(1003,"阿狗3号","33451123qq.com",1,new Department(101,"教学部")));
        employeeMap.put(1004,new Employee(1004,"阿狗4号","33451124qq.com",0,new Department(101,"教学部")));
        employeeMap.put(1005,new Employee(1005,"阿狗5号","33451125qq.com",1,new Department(101,"教学部")));
    }

    //通过id获得员工
    public Employee getEmploteeById(Integer id){
        return employeeMap.get(id);
    }

    private static Integer id = 1005;
    //增加员工
    public void add(Employee employee){
        if(employee==null){
            return;
        }
        if(employee.getId() == null){
            employee.setId(++id);
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employeeMap.put(employee.getId(),employee);
    }

    //删除一个员工
    public void delete(Integer id){
        if(employeeMap.get(id) == null){
            return;
        }
        employeeMap.remove(id);
    }

}
