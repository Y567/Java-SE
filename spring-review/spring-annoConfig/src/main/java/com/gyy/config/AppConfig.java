package com.gyy.config;

import com.gyy.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration             //指明该类是一个配置类，相当于以前的spring配置文件
@ComponentScan("com.gyy")  //指定扫描的包的路径
@Import(value = {AppConfig1.class})  //传入一个或多个class参数，相当于之前配置文件的import标签
public class AppConfig {

    @Bean(name = "user")  //用在方法级别上，默认使用方法名作为注入的对象的名字，也可以用name来指定
    public User getUser(){
        return new User();
    }
}
