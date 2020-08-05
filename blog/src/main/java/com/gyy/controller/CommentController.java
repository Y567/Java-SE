package com.gyy.controller;


import com.gyy.pojo.Comment;
import com.gyy.pojo.User;
import com.gyy.service.BlogService;
import com.gyy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    //注入配置文件中的评论公用头像的地址
    @Value("${comment.avatar}")
    private String avatar;


    //更新评论片段返回前端进行展示
    @GetMapping("/comments/{blogId}")
    public String commentList(@PathVariable Long blogId, Model model){
        model.addAttribute("comments",commentService.listCommentByBlogIdAndParentCommentNull(blogId));
//        System.out.println(commentService.listCommentByBlogId(blogId));
        return "blog :: commentList";
    }

    //点击发布后的处理
    @PostMapping("/comments")
    public String commentInput(Comment comment, HttpSession session){
        Long blogId = comment.getBlog().getId();
//        需要保存评论信息，设置评论所属的blog
        comment.setBlog(blogService.getBlog(blogId));
        User user = (User) session.getAttribute("user");
        if(user != null){
            //是管理员
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }else{
            comment.setAvatar(avatar);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/"+ blogId;
    }

}
