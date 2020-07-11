package com.gyy.controller.admin;


import com.gyy.pojo.User;
import com.gyy.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userServiceImpl;

    //这个方法就是为了跳转到登录界面
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }



    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session,
                        RedirectAttributes redirectAttributes){
//        System.out.println(DigestUtils.md5Hex(password));
        User user = userServiceImpl.findByUsernameAndPassword(username, DigestUtils.md5Hex(password));
//        System.out.println("================================");
        if(user != null){
            //说明用户存在，那么转发到首页
            //转发到前端前，将密码改为null
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else{
            //用户不能存在，给前端进行提示
            //由于重定向后数据会丢失，所以使用redirectAttributes不会
//            System.out.println("没有查到");
            redirectAttributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")  //注销的功能
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
