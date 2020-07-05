package com.gyy.Semaphore;

import java.util.concurrent.Semaphore;

/**
 * 信号量的使用；
 * 案例：抢车位
 */
public class Test {

    public static void main(String[] args) {
//        3个共享资源
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                try {
                    //得到信号量-1
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到车位");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //释放，信号量+1
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName()+"离开车位");
                }
            },String.valueOf(i)).start();


        }
    }
}
