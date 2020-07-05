package com.gyy.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool3 {

    /**
     * 自定义线程池
     */
    public static void main(String[] args) {
//        ExecutorService es = Executors.newCachedThreadPool();//1.0遇强则强，遇弱则弱，最大可创建21亿个线程池
//        ExecutorService es = Executors.newFixedThreadPool(5);//2.0固定大小的线程池
        ExecutorService es = Executors.newSingleThreadExecutor();//3.0只有一个线程池
        for (int i = 0; i < 10; i++) {
            es.execute(()->{
                System.out.println(Thread.currentThread().getName()+"ok");
            });
        }
    }
}
