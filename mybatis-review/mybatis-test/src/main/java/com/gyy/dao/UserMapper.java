package com.gyy.dao;

import com.gyy.domain.User;

import java.util.List;

public interface UserMapper {
    /**
     * 查询所有的用户
     * @return
     */
    List<User> getUserList();
}
