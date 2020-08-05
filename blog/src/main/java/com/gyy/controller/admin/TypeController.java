package com.gyy.controller.admin;

import com.gyy.pojo.Type;
import com.gyy.service.TypeService;
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
public class TypeController {

    @Autowired
    private TypeService typeServiceImpl;
    @GetMapping("/types")
    public String types(@PageableDefault(size = 7,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        //调用findAll查询部分数据
        model.addAttribute("page", typeServiceImpl.listType(pageable));
        return "admin/types";
    }

    //跳转到新增分类页面
    @GetMapping("/types/input")
    public String input(Model model){
        //这里new一个空对象就是为了给前端传一个name
        model.addAttribute("type",new Type());
        return "admin/type-input";
    }

    //跳转到更新页面
    @GetMapping("/types/{id}/update")
    public String editInput(@PathVariable Long id,Model model){
        Type type = typeServiceImpl.getType(id);
        model.addAttribute("type",type);
        return "admin/type-update";
    }

    //进行删除
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes redirectAttributes){
        typeServiceImpl.deleteType(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }

    @PostMapping("/types")  //这里会将传过来的参数封装成type对象,@Validated用来校验对象，result来接收校验过的对象
    public String types(@Validated Type type, BindingResult result, RedirectAttributes redirectAttributes){
        //我们去数据库里查询一下有没有该分类
        Type typeByName = typeServiceImpl.getTypeByName(type.getName());
        if(typeByName != null){
            //数据库中已经有了
            result.rejectValue("name","nameError","该分类已存在");
            return "admin/type-input";
        }
        if(result.hasErrors()){
            //有错误
            return "admin/type-input";
        }
        Type t = typeServiceImpl.saveType(type);
        if(t == null){
            //添加失败
            redirectAttributes.addFlashAttribute("message","新增失败");
        }else{
            redirectAttributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/types";
    }

    @PostMapping("/types/update/{id}")  //更新页面
    public String editTypes(@Validated Type type, BindingResult result, @PathVariable Long id, RedirectAttributes redirectAttributes){
        //我们去数据库里查询一下有没有该分类
        Type typeByName = typeServiceImpl.getTypeByName(type.getName());
        if(typeByName != null){
            //数据库中已经有了
            result.rejectValue("name","nameError","该分类已存在");
            return "admin/type-update";
        }
        if(result.hasErrors()){
            //有错误
            return "admin/type-update";
        }
        Type t = typeServiceImpl.updateType(id,type);
        if(t == null){
            //添加失败
            redirectAttributes.addFlashAttribute("message","更新失败");
        }else{
            redirectAttributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/types";
    }
}
