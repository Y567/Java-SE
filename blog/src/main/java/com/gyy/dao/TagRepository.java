package com.gyy.dao;

import com.gyy.pojo.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    //根据名字查询标签是否存在
    Tag findByName(String name);

    //查询巅峰分类
    @Query("select t from Tag t")
    List<Tag> findTopBy(Pageable pageable);
}
