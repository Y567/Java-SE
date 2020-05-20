package com.gyy.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
//下面这个注解的作用是将该对象和配置文件联系起来，并将配置的信息注入到对象中
@ConfigurationProperties(prefix = "person")
public class Person {
    private String name;
    private List<String> hobby;
    private Map<String,Object> map;
    private Dog dog;


    public Person() {
    }

    public Person(String name, List<String> hobby, Map<String, Object> map, Dog dog) {
        this.name = name;
        this.hobby = hobby;
        this.map = map;
        this.dog = dog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getHobby() {
        return hobby;
    }

    public void setHobby(List<String> hobby) {
        this.hobby = hobby;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", hobby=" + hobby +
                ", map=" + map +
                ", dog=" + dog +
                '}';
    }
}
