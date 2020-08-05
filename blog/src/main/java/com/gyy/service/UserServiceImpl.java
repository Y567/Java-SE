package com.gyy.service;

import com.gyy.dao.UserRepository;
import com.gyy.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username,password);
    }
}
