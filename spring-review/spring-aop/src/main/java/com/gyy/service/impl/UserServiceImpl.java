package com.gyy.service.impl;

import com.gyy.service.UserService;

/**
 * 业务实现类
 */
public class UserServiceImpl implements UserService {
    public void insert() {
        System.out.println("增加用户");
    }

    public void select() {
        System.out.println("查询用户");
    }

    public void update() {
        System.out.println("更新用户");
    }

    public void delete() {
        System.out.println("删除用户");
    }
}
