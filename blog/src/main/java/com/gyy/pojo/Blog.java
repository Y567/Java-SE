package com.gyy.pojo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 核心类：代表一篇博客包含的内容
 */
@Entity  //对应数据库生成表
@Table(name = "t_blog")
public class Blog {

    @Id               //主键
    @GeneratedValue   //id生成规则
    private Long id;              //id
    private String title;         //博客的标题
    private String content;       //博客内容
    private String firstPicture;  //首图
    private String flag = "原创"; //标签，原创，转载？
    private Integer views;        //浏览次数
    private boolean appreciation; //赞赏是否开启
    private boolean shareStatement;//转载声明是否开启
    private boolean commentabled; //是否评论
    private boolean published;    //是否发布
    private boolean recommend;    //是否推荐
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;      //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;      //更新时间

    @Transient
    private String tagIds;        //前端传入过来的标签属性id,我们需要用这个属性去查询所属标签

    //多个Blog对应一个分类
    @ManyToOne
    private Type type;            //分类
    //一个博客有多个标签
    @ManyToMany(cascade = {CascadeType.PERSIST}) //级联新增，涉及到数据库
    private List<Tag> tags = new ArrayList<>();
    //博客属于一个用户
    @ManyToOne
    private User user;
    //一个博客包含多个评论
    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new LinkedList<>(); //经常进行增加，删除，用linked比较好

    private String description; //简介描述用来展示

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Blog() {
    }

    public Blog(String title, String content, String firstPicture, String flag, Integer views, boolean appreciation, boolean shareStatement, boolean commentabled, boolean published, boolean recommend, Date createTime, Date updateTime, String tagIds, Type type, List<Tag> tags, User user, List<Comment> comments, String description) {
        this.title = title;
        this.content = content;
        this.firstPicture = firstPicture;
        this.flag = flag;
        this.views = views;
        this.appreciation = appreciation;
        this.shareStatement = shareStatement;
        this.commentabled = commentabled;
        this.published = published;
        this.recommend = recommend;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.tagIds = tagIds;
        this.type = type;
        this.tags = tags;
        this.user = user;
        this.comments = comments;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public boolean isCommentabled() {
        return commentabled;
    }

    public void setCommentabled(boolean commentabled) {
        this.commentabled = commentabled;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public String toTagIds(){
        StringBuilder sb = new StringBuilder();
        //将集合类型的tags转换为字符串表示的数字
        if(!tags.isEmpty()){
            //不为空就是说明有标签
            for (int i = 0; i < tags.size(); i++) {
                if(i == tags.size() - 1){
                    //最后一个
                    sb.append(tags.get(i).getId());
                }else{
                    sb.append(tags.get(i).getId()).append(",");
                }
            }
        }
        return sb.toString();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", appreciation=" + appreciation +
                ", shareStatement=" + shareStatement +
                ", commentabled=" + commentabled +
                ", published=" + published +
                ", recommend=" + recommend +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", tagIds='" + tagIds + '\'' +
                ", type=" + type +
                ", tags=" + tags +
                ", user=" + user +
                ", comments=" + comments +
                ", description='" + description + '\'' +
                '}';
    }
}
