package com.gyy.pojo;

public class User {

    private String name;

    public User(){
        System.out.println("无参构造执行了");
    }
    public User(String name) {
        //有参构造来创建
        System.out.println("有参构造执行了");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
