package com.gyy;

import com.gyy.mapper.UserMapper;
import com.gyy.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootMybatisApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {

        List<User> user = userMapper.findUser();
        System.out.println(user);

        System.out.println("下面是更新用户");
        User user1 = new User(2, "小黑", "cqc");
        int i = userMapper.updateUser(user1);
        System.out.println(i);
    }

}
