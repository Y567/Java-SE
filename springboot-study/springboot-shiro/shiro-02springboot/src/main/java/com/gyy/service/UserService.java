package com.gyy.service;

import com.gyy.pojo.User;

public interface UserService {
    User findUserByUsername(String username);
}
