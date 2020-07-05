package com.gyy.function;

import java.util.function.Function;

/**
 * 函数型接口
 * function:传入一个T,R返回R，可以用lambda表达式简化
 */
public class Demo1 {
    public static void main(String[] args) {
//        Function<String, String> fun = new Function<String,String>() {
//            @Override
//            public String apply(String o) {
//                return o;
//            }
//        };

        Function<String,String> fun = (r)->{return r;};

        String love = fun.apply("love");
        System.out.println(love);
    }
}
