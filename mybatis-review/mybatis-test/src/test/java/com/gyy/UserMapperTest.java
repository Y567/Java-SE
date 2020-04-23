package com.gyy;

import com.gyy.dao.UserMapper;
import com.gyy.domain.User;
import com.gyy.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void test2() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        //操作数据库的两种方式
        //方式一
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.insertUser(new User(4,"阿狗4号","cqc112233"));

        //增删改需要提交事务
        sqlSession.commit();

        //关闭
        sqlSession.close();
    }

    @Test
    public void test3() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        //操作数据库的两种方式
        //方式一
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findUserById(3);

        System.out.println(user);
        //关闭
        sqlSession.close();
    }

    @Test
    public void test4() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        //操作数据库的两种方式
        //方式一
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.updateUser(new User(4,"阿狗44号","cqc112233"));

        //增删改需要提交事务
        sqlSession.commit();

        //关闭
        sqlSession.close();
    }

    @Test
    public void test5() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        //操作数据库的两种方式
        //方式一
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.deleteUser(4);

        //增删改需要提交事务
        sqlSession.commit();

        //关闭
        sqlSession.close();
    }

    @Test
    public void test6() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        //操作数据库的两种方式
        //方式一
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("uid",1);
        List<User> users = userMapper.getUserByMap(map);
        System.out.println(users);

        //关闭
        sqlSession.close();
    }

    @Test
    public void test7() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        //操作数据库的两种方式
        //方式一
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        List<User> user = userMapper.getUserByLike("阿%");
        System.out.println(user);

        //关闭
        sqlSession.close();
    }
}
