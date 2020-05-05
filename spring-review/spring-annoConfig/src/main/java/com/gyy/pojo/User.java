package com.gyy.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("user1")  //可以在起一个名字
public class User {

    @Value("阿狗")
    private String name;

}
