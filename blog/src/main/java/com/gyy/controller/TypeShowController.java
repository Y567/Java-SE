package com.gyy.controller;


import com.gyy.pojo.Type;
import com.gyy.service.BlogService;
import com.gyy.service.TypeService;
import com.gyy.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    //查询所有的分类
    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size = 7, sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, @PathVariable Long id, Model model){
        List<Type> types = typeService.listTypeTop(10000);
        if(id == -1){
            //默认设置第一个最高的分类
            id = types.get(0).getId();
        }
        //相当于查取所有的分类了
        model.addAttribute("types",types);
        BlogQuery blog = new BlogQuery();
        //设置需要查取的类型
        blog.setTypeId(id);
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        //还需要将这个id传送回去因为需要激活按钮
        model.addAttribute("activeTypeId",id);
        return "types";
    }
}
