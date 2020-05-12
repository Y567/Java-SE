package com.gyy.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interceptor")
public class TestController {
    @RequestMapping("/say")
    public String sayHello(){
        return "Hello";
    }
}
