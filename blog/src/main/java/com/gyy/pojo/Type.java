package com.gyy.pojo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * 类型
 */
@Entity  //对应数据库生成表
@Table(name = "t_type")
public class Type {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "分类名称不可以为空")
    private String name;

    //一对多，一个Type有多个Blog
    @OneToMany(mappedBy = "type",cascade = CascadeType.REMOVE)
    private List<Blog> blogs = new ArrayList<>();

    public Type() {
    }

    public Type(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
