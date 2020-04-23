package com.gyy.dao;

import com.gyy.domain.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    /**
     * 查询所有的用户
     * @return
     */
    List<User> getUserList();

    /**
     * 按照id查询用户名
     * @param id  id
     * @return  User
     */
    User findUserById(int id);

    /**
     * 插入一个用户
     * @param user user
     * @return  返回影响的行数
     */
    void insertUser(User user);

    /**
     * 更新指定的用户
     * @param user user
     */
    void updateUser(User user);

    /**
     * 按照id进行用户删除
     * @param id id
     */
    void deleteUser(int id);

    /**
     * 利用map保存查询条件
     * @param map map
     * @return 返回用户数据
     */
    List<User> getUserByMap(Map<String,Object> map);

    List<User> getUserByLike(String username);
}
