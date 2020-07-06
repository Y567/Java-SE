package com.gyy.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo1 {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(22);
        //这一句函数是调用了底层的C++，进行加操作
        boolean b = atomicInteger.compareAndSet(22, 25);
        System.out.println(b);
        atomicInteger.getAndIncrement();
        System.out.println(atomicInteger.get());
    }
}
