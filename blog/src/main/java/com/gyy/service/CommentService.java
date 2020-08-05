package com.gyy.service;

import com.gyy.pojo.Comment;

import java.util.List;

public interface CommentService {

    //根据博客的Id获取到评论
    List<Comment> listCommentByBlogIdAndParentCommentNull(Long blogId);

    //保存评论
    Comment saveComment(Comment comment);
}
