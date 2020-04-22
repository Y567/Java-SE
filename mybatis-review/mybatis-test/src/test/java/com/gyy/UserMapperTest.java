package com.gyy;

import com.gyy.dao.UserMapper;
import com.gyy.domain.User;
import com.gyy.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class UserMapperTest {

    @Test
    public void test1() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        //操作数据库的两种方式
        //方式一
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.getUserList();
        for (User user : userList) {
            System.out.println(user);
        }

        //关闭
        sqlSession.close();
        //fa方式二
    }
}
