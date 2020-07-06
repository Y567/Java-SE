package com.gyy.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 原子引用解决ABA问题
 */
public class AtomicStamp {

    public static void main(String[] args) {

        //原子引用
        AtomicStampedReference<Integer> stamp = new AtomicStampedReference<>(20, 1);

        new Thread(()->{
            int s = stamp.getStamp();
            System.out.println("版本号为"+s);

            try {
                TimeUnit.SECONDS.sleep(3);
                //改变值
                boolean flag = stamp.compareAndSet(20, 21, s, stamp.getStamp() + 1);
                System.out.println("A改变成功了嘛"+flag);
                boolean flag2 = stamp.compareAndSet(21, 20, stamp.getStamp(), stamp.getStamp() + 1);
                System.out.println("A变回成功了嘛"+flag2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"A").start();

        new Thread(()->{
            int s = stamp.getStamp();
            System.out.println("版本号为"+s);
            try {
                TimeUnit.SECONDS.sleep(5);
                boolean flag = stamp.compareAndSet(20, 66, s, stamp.getStamp() + 1);
                System.out.println("B改变成功了嘛"+flag);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();

    }
}
