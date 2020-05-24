package com.gyy.service;

import java.util.Map;

public interface PoetryService {
    /**
     * 根据用户传上来的参数进行查询
     * map里面存储的是左下标和右下标
     */
    String findByAmount(Map<String,Integer> map);

    /**
     * 查询词语
     * @param name 按照诗人的姓名
     */
    String findWordsByName(String name);

    /**
     * 按照名字的个数查找诗人
     * @param i 名字的个数
     */
    String findPoetByName(int i);
}
