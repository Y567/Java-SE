package com.gyy.controller;

import com.gyy.pojo.Tag;
import com.gyy.service.BlogService;
import com.gyy.service.TagService;
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
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    //查询所有的分类
    @GetMapping("/tags/{id}")
    public String tags(@PageableDefault(size = 7, sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, @PathVariable Long id, Model model){
        List<Tag> tags = tagService.listTagTop(10000);
        if(id == -1){
            //默认设置第一个最高的分类
            id = tags.get(0).getId();
        }
        //相当于查取所有的分类了
        model.addAttribute("tags",tags);
        model.addAttribute("page",blogService.listBlog(id,pageable));
        //还需要将这个id传送回去因为需要激活按钮
        model.addAttribute("activeTagId",id);
        return "tags";
    }
}
