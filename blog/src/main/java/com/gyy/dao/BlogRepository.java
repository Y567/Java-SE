package com.gyy.dao;

import com.gyy.pojo.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 后面的接口是用来辅助动态查询的
 */
public interface BlogRepository extends JpaRepository<Blog,Long>, JpaSpecificationExecutor<Blog> {
    //查询top的推荐
    @Query("select b from Blog b where b.recommend=true")
    List<Blog> findTopBy(Pageable pageable);

    //搜索功能
    @Query("select b from Blog b where b.title like ?1 or b.content like ?1")
    Page<Blog> findByQuery(String query, Pageable pageable);

    //浏览次数增加
    @Modifying
    @Query("update Blog b set b.views = b.views+1 where b.id = ?1")
    int updateViews(Long id);

    //查取年份
    @Query("select function('date_format',b.createTime,'%Y-%m') as year from Blog b group by function('date_format',b.createTime,'%Y-%m') order by year desc")
    List<String> findGroupYear();

    //根据年份去查博客集合
    @Query("select b from Blog b where function('date_format',b.createTime,'%Y-%m') = ?1")
    List<Blog> findByYear(String year);
}
