package com.gyy.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtil {

    //获得操作数据库对象的工厂
    private static SqlSessionFactory sqlSessionFactory;

    static{
        try {
            String path = "mybatis-config.xml";
            InputStream in = Resources.getResourceAsStream(path);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取sqlSession对象,设置为true为自动提交，一般不要这样设置
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession(true);
    }
}
