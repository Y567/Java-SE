package com.gyy.controller.admin;

import com.gyy.pojo.Tag;
import com.gyy.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagServiceImpl;
    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 7,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        //调用findAll查询部分数据
        model.addAttribute("page", tagServiceImpl.listTag(pageable));
        return "admin/tags";
    }

    //跳转到新增分类页面
    @GetMapping("/tags/input")
    public String input(Model model){
        //这里new一个空对象就是为了给前端传一个name
        model.addAttribute("tag",new Tag());
        return "admin/tag-input";
    }

    //跳转到更新页面
    @GetMapping("/tags/{id}/update")
    public String editInput(@PathVariable Long id, Model model){
        Tag tag = tagServiceImpl.getTag(id);
        model.addAttribute("tag",tag);
        return "admin/tag-update";
    }

    //进行删除
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes){
        tagServiceImpl.deleteTag(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }

    @PostMapping("/tags")  //这里会将传过来的参数封装成tag对象,@Validated用来校验对象，result来接收校验过的对象
    public String tags(@Validated Tag tag, BindingResult result, RedirectAttributes redirectAttributes){
        //我们去数据库里查询一下有没有该分类
        Tag tagByName = tagServiceImpl.getTagByName(tag.getName());
        if(tagByName != null){
            //数据库中已经有了
            result.rejectValue("name","nameError","该标签已存在");
            return "admin/tag-input";
        }
        if(result.hasErrors()){
            //有错误
            return "admin/tag-input";
        }
        Tag t = tagServiceImpl.saveTag(tag);
        if(t == null){
            //添加失败
            redirectAttributes.addFlashAttribute("message","新增失败");
        }else{
            redirectAttributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/tags";
    }

    @PostMapping("/tags/update/{id}")  //更新页面
    public String editTags(@Validated Tag tag, BindingResult result, @PathVariable Long id, RedirectAttributes redirectAttributes){
        //我们去数据库里查询一下有没有该分类
        Tag tagByName = tagServiceImpl.getTagByName(tag.getName());
        if(tagByName != null){
            //数据库中已经有了
            result.rejectValue("name","nameError","该标签已存在");
            return "admin/tag-update";
        }
        if(result.hasErrors()){
            //有错误
            return "admin/tag-update";
        }
        Tag t = tagServiceImpl.updateTag(id,tag);
        if(t == null){
            //添加失败
            redirectAttributes.addFlashAttribute("message","更新失败");
        }else{
            redirectAttributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/tags";
    }
}
