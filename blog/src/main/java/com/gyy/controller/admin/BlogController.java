package com.gyy.controller.admin;

import com.gyy.pojo.Blog;
import com.gyy.pojo.Type;
import com.gyy.pojo.User;
import com.gyy.service.BlogService;
import com.gyy.service.TagService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * 博客管理页面
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT = "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";

    @Autowired
    private BlogService blogServiceImpl;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    //列表首页
    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 7, sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog, Model model){
        //顺便将分类类型也查出来
        model.addAttribute("types",typeService.listType());
        model.addAttribute("page",blogServiceImpl.listBlog(pageable, blog));
        return LIST;
    }

    //查询博文
    @RequestMapping("/blogs/search")
    public String search(@PageableDefault(size = 7, sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog, Model model){
        model.addAttribute("page",blogServiceImpl.listBlog(pageable, blog));
        //更新一个片段
        return "admin/blogs :: blogList";
    }

    //跳转到新增博文
    @GetMapping("/blogs/input")
    public String input(Model model){
        //查询数据信息
        model.addAttribute("types",typeService.listType());
        //查询所有的标签，可以进行选择
        model.addAttribute("tags",tagService.listTag());
        Blog blog = new Blog();
        blog.setContent("请开始你的表演~~~");
        blog.setType(new Type());
        model.addAttribute("blog",blog);
        return INPUT;
    }

    //编辑博文
    @RequestMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        //查询数据信息
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        Blog blog = blogServiceImpl.getBlog(id);
        //tagIds是一个字符串
        blog.setTagIds(blog.toTagIds());
        model.addAttribute("blog",blog);
        return INPUT;
    }

    //新增博文
    @PostMapping("/blogs")
    public String post(Blog blog, HttpSession session, RedirectAttributes redirectAttributes){
        //设置基本的博文属性值
        blog.setUser((User) session.getAttribute("user"));
        //通过前端传来的id查询对应的type
        blog.setType(typeService.getType(blog.getType().getId()));
        //设置标签,通过字符串id获取一个标签集合
        blog.setTags(tagService.listTag(blog.getTagIds()));
        //保存一篇博文
        Blog b = blogServiceImpl.saveBlog(blog);
        if(b == null){
            redirectAttributes.addFlashAttribute("message","操作失败");
        }else{
            redirectAttributes.addFlashAttribute("message","操作成功");
        }
        return REDIRECT_LIST;
    }

    //删除博文
    @RequestMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes){
        blogServiceImpl.deleteBlog(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return REDIRECT_LIST;
    }

}
