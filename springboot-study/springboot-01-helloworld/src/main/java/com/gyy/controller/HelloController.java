package com.gyy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/springboot")
    public String syHello(){
        return "你好springBoot高洋洋来了";
    }
}
