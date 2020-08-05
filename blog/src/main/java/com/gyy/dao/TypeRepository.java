package com.gyy.dao;

import com.gyy.pojo.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
    //根据名字查询分类是否存在
    Type findByName(String name);

    //查询巅峰分类
    @Query("select t from Type t")
    List<Type> findTopBy(Pageable pageable);
}
