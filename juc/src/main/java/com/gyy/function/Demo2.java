package com.gyy.function;

import java.util.function.Predicate;

/**
 * 断定型接口
 * pre:传入一个参数，经过判断可以返回一个布尔值，这就是断定型函数式接口，可以简化
 */
public class Demo2 {
    public static void main(String[] args) {
//        Predicate<String> predicate = new Predicate<String>() {
//            @Override
//            public boolean test(String s) {
//                return s.isEmpty();
//            }
//        };
        Predicate<String> predicate = (s)->{return s.isEmpty();};
        System.out.println(predicate.test("love"));
    }
}
