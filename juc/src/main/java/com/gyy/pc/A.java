package com.gyy.pc;

/**
 * synchronized版本的生产者和消费者
 */

import sun.text.normalizer.CharTrie;

/**
 * 资源类
 */
class Data {
    private int number = 0;

    public synchronized void increment() throws InterruptedException {
        while(number != 0){
            //有资源就不加了
            this.wait();
        }
        ++number;
        System.out.println("加1完毕"+"=>"+number);
        //唤醒消费者
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        while(number == 0){
            this.wait();
        }
        --number;
        System.out.println("减1完毕"+"=>"+number);
        //唤醒生产者
        this.notifyAll();
    }
}
public class A {
    public static void main(String[] args) {
        Data data = new Data();

        new Thread(()->{
            try {
                for (int i = 0; i < 10; i++) {
                    data.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();
        new Thread(()->{
            try {
                for (int i = 0; i < 10; i++) {
                    data.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();

        new Thread(()->{
            try {
                for (int i = 0; i < 10; i++) {
                    data.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"C").start();

        new Thread(()->{
            try {
                for (int i = 0; i < 10; i++) {
                    data.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"D").start();
    }
}
