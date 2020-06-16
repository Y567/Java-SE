package com.gyy.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class MyController {


    @RequestMapping({"/","/index"})
    public String toIndex(Model model){
        model.addAttribute("msg","阿狗");
        return "index";
    }

    @RequestMapping("/add")
    public String toAdd(){
        return "add";
    }

    @RequestMapping("/update")
    public String toUpdate(){
        return "update";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,Model model){
        //获取用户
        Subject currentUser = SecurityUtils.getSubject();

        //创建一个token进行登录
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try{
            //调用这里的方法后，需要重写认证的代码，也就是和数据相连接的realM
            currentUser.login(token);
            return "login";
        }catch (UnknownAccountException e){
            model.addAttribute("msg1","用户名不存在");
            return "login";
        }catch(IncorrectCredentialsException e){
            model.addAttribute("msg1","密码错误");
            return "login";
        }
    }
}
