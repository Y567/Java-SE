package com.gyy.controller;

import com.gyy.service.BlogService;
import com.gyy.service.CommentService;
import com.gyy.service.TagService;
import com.gyy.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * 首页controller
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;

    @GetMapping({"/","/index"})
    public String toIndex(@PageableDefault(size = 7, sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        //查询博客
        model.addAttribute("page",blogService.listBlog(pageable));
        //查询分类
        model.addAttribute("types",typeService.listTypeTop(6));
        //查询标签
        model.addAttribute("tags",tagService.listTagTop(10));
        //查询最新推荐
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(8));

        return "index";
    }

    //处理搜索功能的controller
    @PostMapping("/search")
    public String search(@PageableDefault(size = 7, sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,String query, Model model){
        model.addAttribute("page",blogService.listBlog("%"+query+"%",pageable));
        model.addAttribute("query",query);
        return "search";
    }

    //查看博文
    @GetMapping("/blog/{id}")
    public String detail(@PathVariable Long id,Model model){
        model.addAttribute("blog",blogService.getAndConvert(id));
        model.addAttribute("comments",commentService.listCommentByBlogIdAndParentCommentNull(id));
//        System.out.println(blogService.getAndConvert(id));
        return "blog";
    }
}
