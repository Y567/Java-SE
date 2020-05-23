package com.gyy.dao;

import com.gyy.pojo.Echart;

import java.util.List;
import java.util.Map;

public interface PoetryMapper {
    /**
     * 根据用户传上来的参数进行查询
     * map里面存储的是左下标和右下标
     */
    List<Echart> findByAmount(Map<String,String> map);

    /**
     * 查询词语
     * @param name 按照诗人的姓名
     */
    String findWordsByName(String name);

    /**
     * 按照名字的个数查找诗人
     * @param i 名字的个数
     */
    List<String> findPoetByName(int i);
}
