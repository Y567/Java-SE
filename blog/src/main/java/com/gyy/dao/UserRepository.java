package com.gyy.dao;

import com.gyy.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  //JPA继承JpaRepository后就会生成基本的增删改查
public interface UserRepository extends JpaRepository<User,Long> {
    //检查用户是否存在
    User findByUsernameAndPassword(String username,String password);
}
