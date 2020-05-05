package com.gyy.dao;

import com.gyy.pojo.User;

import java.util.List;

public interface UserMapper {
    List<User> findUser();
}
