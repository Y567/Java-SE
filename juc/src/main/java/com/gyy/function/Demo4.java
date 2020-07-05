package com.gyy.function;

import java.util.function.Supplier;

/**
 * 供给型接口
 *不需要传入参数就可以返回数据，可以简化
 */
public class Demo4 {

    public static void main(String[] args) {
//        Supplier<String> su = new Supplier<String>() {
//            @Override
//            public String get() {
//                return "yaseilalei";
//            }
//        };

        Supplier<String> su = ()->{return "yaseilalei";};

        System.out.println(su.get());
    }
}
