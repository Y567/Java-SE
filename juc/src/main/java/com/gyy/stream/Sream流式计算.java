package com.gyy.stream;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Sream流式计算 {
    /**
     * 需求：选出满足条件的用户
     * 1.ID必须是偶数
     * 2.年龄大于23岁的
     * 3.用户名转为大写字母的
     * 4.用户名字母倒着排序
     * 5.只输出一个用户
     */

    public static void main(String[] args) {
        User u1 = new User(1, "a", 21);
        User u2 = new User(2, "b", 22);
        User u3 = new User(3, "c", 23);
        User u4 = new User(4, "d", 24);
        User u5 = new User(5, "e", 25);
        User u6 = new User(6, "f", 21);

        List<User> lists = Arrays.asList(u1, u2, u3, u4, u5, u6);


        //计算交给流
        lists.stream()
                .filter(u->{return u.getId()%2==0;})
                .filter(u->{return u.getAge()>23;})
                .map(u->{return u.getName().toUpperCase();})
                .sorted((name1,name2)->{return name2.compareTo(name1);})
                .limit(1)
                .forEach(System.out::println);
    }
}
