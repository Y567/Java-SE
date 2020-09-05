package com.gyy.cyclibarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Test {

    public static void main(String[] args) {
        //七个await后就会执行函数
        CyclicBarrier cb = new CyclicBarrier(7, () -> {
            System.out.println("狗子你想死");
        });
        for (int i = 0; i < 7; i++) {
            final int t = i;
            new Thread(()->{
                try {
                    System.out.println("执行完了"+t+"任务");
                    //挂起当前线程，等所有线程都到这一步了在一起执行
                    cb.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
