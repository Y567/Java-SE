package com.gyy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
public class TestController {

    @RequestMapping("/01")
    public String test(Model model){
        model.addAttribute("msg","阿狗你怎恶魔了");
        model.addAttribute("users", Arrays.asList("a", "b"));
        return "test";
    }
}
