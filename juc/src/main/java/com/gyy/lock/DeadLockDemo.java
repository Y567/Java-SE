package com.gyy.lock;


import java.util.concurrent.TimeUnit;

class MyLock {
    private String lock1;
    private String lock2;

    public MyLock(String lock1,String lock2){
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    public void test() throws InterruptedException {
        synchronized (lock1){
            System.out.println(Thread.currentThread().getName()+"获得了"+lock1+"锁，现在需要"+lock2+"锁");
            TimeUnit.SECONDS.sleep(3);
            synchronized (lock2){
                System.out.println("获得了所有的锁哈哈哈，然而作者我不会让你执行的");
            }
        }
    }

}
public class DeadLockDemo {



    public static void main(String[] args) {
        new Thread(()->{
            try {
                new MyLock("第一把锁","第二把锁").test();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        new Thread(()->{
            try {
                new MyLock("第二把锁","第一把锁").test();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
