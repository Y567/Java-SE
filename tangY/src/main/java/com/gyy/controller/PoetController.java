package com.gyy.controller;

import com.gyy.service.PoetryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class PoetController {

    @Resource(name = "poetryService")
    private PoetryService poetryService;

    @RequestMapping("/poet")
    public String findPoet(@RequestParam("name") String name, HttpServletResponse resp) throws IOException {
        //1.调用service层
        String json = poetryService.findPoetByName(Integer.parseInt(name));
        //2.写回数据
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
        return json;
    }
}
