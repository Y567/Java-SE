package com.gyy.volatileDemo;

public class Test2 {
    /**
     * 不能保证原子性
     */
    private volatile static int num = 0;

    public static void add(){
        num++;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    add();
                }
            }).start();
        }

        while(Thread.activeCount()>2){
            Thread.yield();
        }

        System.out.println("结束了"+num);
    }
}
