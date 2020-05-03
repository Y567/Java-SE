package com.gyy;

import com.gyy.dao.StudentMapper;
import com.gyy.domain.Student;
import com.gyy.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SQLTest {

    @Test
    public void test() {

        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        HashMap<String,String> map = new HashMap<String,String>();
        map.put("id","2");
        map.put("name","小爷");

        map.put("tid","5");

//        List students = mapper.getStudentBySQL(map);

//        List<Student> students = mapper.getStudentByChoose(map);

        mapper.updateStudentById(map);
//        System.out.println(students);

        //关闭
        sqlSession.close();
        //fa方式二
    }

    @Test
    public void test1(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        HashMap<String, Object> map = new HashMap<String, Object>();
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        map.put("ids",ids);

        List<Student> students = mapper.getStudentByForeach(map);
        System.out.println(students);

        sqlSession.close();
    }
}
