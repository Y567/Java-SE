package com.gyy.controller;

import com.gyy.service.PoetryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController  //让返回的数据为json格式
public class WordController {

    @Resource(name = "poetryService")
    private PoetryService poetryService;

    @RequestMapping("/word")
    public String findWord(@RequestParam("name") String name, HttpServletResponse resp) throws IOException {

        System.out.println("来到这个方法了"+name);
        //1.获取诗人的名字
        String namePoetry = new String(name.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        //2.调用service层
        String json = poetryService.findWordsByName(namePoetry);
        //3.写回数据
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
        return json;
    }
}
