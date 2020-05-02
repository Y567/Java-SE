package com.gyy;

import com.gyy.dao.TeacherMapper;
import com.gyy.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class UserMapperTest {

    @Test
    public void test() {

        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);

        System.out.println(mapper.findTeacherById(1));

        //关闭
        sqlSession.close();
        //fa方式二
    }

}
