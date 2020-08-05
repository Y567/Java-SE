package com.gyy.service;

import com.gyy.dao.CommentRepository;
import com.gyy.pojo.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    //循环递归添加子节点
    private List<Comment> tempReplys = new ArrayList<>();

    //循环每个顶级的评论也就是第一级评论，把它们的儿子孙子都归为二级评论，我们的页面结构只要两层
    private List<Comment> eachComment(List<Comment> comments){
        //开头在清理一次避免残余评论混入
        tempReplys = new ArrayList<>();
        ArrayList<Comment> commentsView = new ArrayList<>();
        //复制一份集合，防止影响数据库的操作
        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment, c);
            commentsView.add(c);
        }
        //合并评论的各层子代到二级评论
        combineChildren(commentsView);
        return commentsView;
    }

    //合并
    private void combineChildren(List<Comment> commentsView){
        //commentsView里是所有的一级评论
        for(Comment comment:commentsView){
            //获得每一个顶级的直接子代
            List<Comment> replys = comment.getReplyComments();
            for(Comment reply : replys){
                //循环迭代，找出子代,存放在tempReplys中
                recursively(reply);
            }
            //修改顶级节点的所有子孙为直接子代
            comment.setReplyComments(tempReplys);
            //清除临时存放区
            tempReplys = new ArrayList<>();
        }
    }

    //剥洋葱
    private void recursively(Comment comment){
        //第一个节点就是直接子代，看上面的调用过程
        tempReplys.add(comment);
        if(comment.getReplyComments().size() > 0){
            List<Comment> replys = comment.getReplyComments();
            for(Comment reply: replys){
                tempReplys.add(reply);
                if(reply.getReplyComments().size() > 0){
                    recursively(reply);
                }
            }
        }
    }

    @Transactional
    @Override
    public List<Comment> listCommentByBlogIdAndParentCommentNull(Long blogId) {
        Sort sort = Sort.by(Sort.Direction.ASC, "createTime");
        List<Comment> comments = commentRepository.findByBlogIdAndParentCommentNull(blogId, sort);
        return eachComment(comments);
    }

    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        Long parentCommentId = comment.getParentComment().getId();
        if(parentCommentId != -1){
            //二级评论，需要将这个评论的父id设置为一级的
            comment.setParentComment(commentRepository.getOne(parentCommentId));
        }else{
            //防止保存的时候出错，因为controller形成的comment并没有实例化
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }
}
