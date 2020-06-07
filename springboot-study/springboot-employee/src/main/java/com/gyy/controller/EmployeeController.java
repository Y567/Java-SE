package com.gyy.controller;

import com.gyy.dao.EmployeeDao;
import com.gyy.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    //返回全部的员工列表
    @RequestMapping("/list")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getEmployees();
        model.addAttribute("employees",employees);
        return "emp/list";
    }
}
