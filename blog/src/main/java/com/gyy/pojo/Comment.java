package com.gyy.pojo;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 评论类
 */
@Entity  //对应数据库生成表
@Table(name = "t_comment")
public class Comment {

    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;  //头像可以存地址
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    //一个评论属于一个博客
    @ManyToOne
    private Blog blog;
    //一个评论有多个回复评论
    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replyComments = new LinkedList<>();
    //一个评论只能回复一个评论（父类）,多个评论都是回答一个父类问题
    @ManyToOne
    private Comment parentComment;

    private boolean adminComment;

    public Comment() {
    }

    public Comment(String nickname, String email, String content, String avatar, Date createTime, Blog blog, List<Comment> replyComments, Comment parentComment, boolean adminComment) {
        this.nickname = nickname;
        this.email = email;
        this.content = content;
        this.avatar = avatar;
        this.createTime = createTime;
        this.blog = blog;
        this.replyComments = replyComments;
        this.parentComment = parentComment;
        this.adminComment = adminComment;
    }

    public boolean isAdminComment() {
        return adminComment;
    }

    public void setAdminComment(boolean adminComment) {
        this.adminComment = adminComment;
    }

    public List<Comment> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<Comment> replyComments) {
        this.replyComments = replyComments;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createTime=" + createTime +
                ", blog=" + blog +
                ", replyComments=" + replyComments +
                ", parentComment=" + parentComment +
                ", adminComment=" + adminComment +
                '}';
    }
}
