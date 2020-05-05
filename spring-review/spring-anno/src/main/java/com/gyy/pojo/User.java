package com.gyy.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component //相当于<bean id="user" class="com.gyy.pojo.User"><property name="name" value="阿狗"/></bean>
//不同的是，注解的方式不需要set方法
public class User {

    @Value("阿狗")  //属性注入
    public String name;
}
