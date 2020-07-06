package com.gyy.zixuan;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        MyLock myLock = new MyLock();
        new Thread(()->{
            myLock.lock();
            try {
                System.out.println("A线程在使用呢");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                myLock.unlock();
                System.out.println("AIsOver");
            }
        },"A").start();

        Thread.sleep(1000);

        new Thread(()->{
            myLock.lock();
            try {
                System.out.println("B线程在使用呢");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                myLock.unlock();
            }
        },"B").start();


    }
}
