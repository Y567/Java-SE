package com.gyy.dao;

import com.gyy.pojo.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    //根据博客id获取评论
    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);
}
