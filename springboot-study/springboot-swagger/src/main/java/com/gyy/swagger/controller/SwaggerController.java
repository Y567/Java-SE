package com.gyy.swagger.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SwaggerController {

    @RequestMapping("/swagger")
    public String swaggerHello(){
        return "helloSwagger";
    }
}
