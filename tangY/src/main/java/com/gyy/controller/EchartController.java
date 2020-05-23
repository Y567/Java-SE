package com.gyy.controller;

import com.gyy.service.PoetryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@RestController
public class EchartController {

    @Resource(name = "poetryService")
    private PoetryService poetryService;

    @RequestMapping("/echart")
    public String findEchart(@RequestParam("left") String left, @RequestParam("right") String right, HttpServletResponse resp) throws IOException {
        //1.构造传上来的参数为map，因为dao层是利用万能map的(重构后，left和right就是)
        System.out.println("走到这里来了"+left+"      right"+right);
        HashMap<String, String> map = new HashMap<>();
        map.put("left",left);
        map.put("right",right);
        //2.调用service层
        String json = poetryService.findByAmount(map);
        //3.响应数据
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
        return json;
    }
}
