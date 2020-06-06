package com.gyy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class LoginController {

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model){
        if(username == null || !username.equals("admin")){
            model.addAttribute("msg","用户名错误");
            return "index";
        }
        if(password == null || !password.equals("123456")){
            model.addAttribute("msg","密码错误");
            return "index";
        }
        //这里给了一个假路径,其实被我们映射到主页了
        return "redirect:/main.html";
    }
}
