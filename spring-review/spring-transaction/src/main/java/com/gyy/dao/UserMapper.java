package com.gyy.dao;

import com.gyy.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    /**
     * 查询所有用户
     * @return 返回一个list集合
     */
    List<User> selectUser();

    /**
     * 删除指定用户
     * @param id 根据id进行删除
     */
    void deleteUser(@Param("id") int id);

    /**
     * 插入一个用户
     * @param user 用户对象
     */
    void insertUser(User user);
}
