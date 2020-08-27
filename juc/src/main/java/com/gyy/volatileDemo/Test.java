package com.gyy.volatileDemo;

import java.util.concurrent.TimeUnit;

public class Test {
    /**
     * volatile保证可见性
     */

    private static int num = 0;

    public static void main(String[] args) throws InterruptedException {

        new Thread(()->{
            while(num == 0){
                Thread thread = Thread.currentThread();
            }
        }).start();


        TimeUnit.SECONDS.sleep(5);

        num = 1;
//        System.out.println(num);
    }
}
