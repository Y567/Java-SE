package com.gyy;

import com.gyy.dao.UserMapper;
import com.gyy.domain.User;
import com.gyy.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class UserMapperTest {

    @Test
    public void test1() {

        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //查
        User user = mapper.findUserById(3,"小强");

        //改
        mapper.updateUser(new User(3,"小强你怎么了","12314141"));
        System.out.println(user);

        //插
//        mapper.insertUser(new User(4,"小强你怎么了","12314141"));

        //删
        mapper.deleteUserById(4);

        //关闭
        sqlSession.close();
        //fa方式二
    }

}
