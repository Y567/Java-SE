package com.gyy.function;

import java.util.function.Consumer;

/**
 * 消费型接口
 * 传入参数后无返回值，执行某段代码，可以简化
 */
public class Demo3 {

    public static void main(String[] args) {
//        Consumer<String> consumer = new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        };

        Consumer<String> consumer = (s)->{
            System.out.println(s);
        };
        consumer.accept("love");
    }
}
