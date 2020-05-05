package com.gyy.dao.impl;

import com.gyy.dao.UserMapper;
import com.gyy.pojo.User;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public class UserMapperImpl implements UserMapper {

    //这里的实现类由spring进行装配即可，我们将来直接调用
    private SqlSessionTemplate sqlSession;

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<User> findUser() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.findUser();
    }
}
