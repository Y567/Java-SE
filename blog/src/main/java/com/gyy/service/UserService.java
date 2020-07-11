package com.gyy.service;

import com.gyy.pojo.User;

public interface UserService {

    //检查用户是否存在
    User findByUsernameAndPassword(String username,String password);
}
