package com.gyy.service;

import com.gyy.pojo.Blog;
import com.gyy.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * Blog管理的接口
 */
public interface BlogService {

    //根据id获取博客
    Blog getBlog(Long id);

    //获取并转换
    Blog getAndConvert(Long id);

    //博文管理页面的搜索功能
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    //分页查找博文
    Page<Blog> listBlog(Pageable pageable);

    //标签查询博客
    Page<Blog> listBlog(Long id,Pageable pageable);

    //查找推荐博文前几的方法
    List<Blog> listRecommendBlogTop(Integer size);

    //String代表年份，集合是该年份的所有博客
    Map<String,List<Blog>> archiveBlog();

    //统计一共有多少博客
    Long countBlog();

    //首页的搜索功能
    Page<Blog> listBlog(String query,Pageable pageable);

    //保存博文
    Blog saveBlog(Blog blog);

    //更新博文
    Blog updateBlog(Long id,Blog blog);

    //删除博文
    void deleteBlog(Long id);
}
