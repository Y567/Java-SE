package com.gyy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyy.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
//@RestController   //这个注解表示该类下的所有方法返回的数据都不经过视图解析器（返回了json字符串）
public class JsonController {

    @ResponseBody   //可以让返回的字符串不经过视图解析器
    @RequestMapping("/jackson")
    public String json1() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(new User());
    }
}
