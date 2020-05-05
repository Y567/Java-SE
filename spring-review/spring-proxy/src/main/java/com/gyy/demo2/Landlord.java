package com.gyy.demo2;

/**
 * 真实对象（需要被代理的对象）,实现这个租房接口就可以租房了
 */
public class Landlord implements Rent {
    public void rent() {
        System.out.println("房东（真实对象）租房子");
    }
}
