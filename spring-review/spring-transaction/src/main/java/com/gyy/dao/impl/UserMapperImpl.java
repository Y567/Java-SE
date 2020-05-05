package com.gyy.dao.impl;

import com.gyy.dao.UserMapper;
import com.gyy.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper {

    /**
     * 继承SqlSessionDaoSupport抽象类就是第二种整合方式，可以少sqlSession的注入这一步骤
     */

    public List<User> selectUser() {
        //如果这是一个事务，我们需要保证它的成功与失败不改变数据一致性
        //先插入，后删除，再查询
        SqlSession sqlSession = getSqlSession();
        sqlSession.getMapper(UserMapper.class).insertUser(new User(1,"小白","1234"));
        sqlSession.getMapper(UserMapper.class).deleteUser(1);
        return sqlSession.getMapper(UserMapper.class).selectUser();
    }

    public void deleteUser(int id) {
        getSqlSession().getMapper(UserMapper.class).deleteUser(id);
    }

    public void insertUser(User user) {
        getSqlSession().getMapper(UserMapper.class).insertUser(user);
    }


}
