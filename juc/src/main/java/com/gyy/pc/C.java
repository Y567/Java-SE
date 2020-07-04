package com.gyy.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 资源类
 * 需求：A执行完后执行B，B执行完后执行C
 */
class Data3 {

    //number为1执行A，为2执行B,为3执行C
    private int number = 1;
    private Lock lock = new ReentrantLock();

    //Condition可以准确唤醒指定监视器监视的线程
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    Condition conditionC = lock.newCondition();

    public void printA(){
        lock.lock();
        try{
            while(number != 1){
                conditionA.await();
            }
            number = 2;
            System.out.println("AAAAAAAAAAAAAAAAAAA");
            //唤醒conditionB监视的线程
            conditionB.signal();
        }catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void printB(){
        lock.lock();
        try{
            while(number != 2){
                conditionB.await();
            }
            number = 3;
            System.out.println("BBBBBBBBBBBBBBBBBBB");
            //唤醒conditionC监视的线程
            conditionC.signal();
        }catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void printC(){
        lock.lock();
        try{
            while(number != 3){
                //等待与唤醒要格外注意
                conditionC.await();
            }
            number = 1;
            System.out.println("CCCCCCCCCCCCCCCCCCC");
            //唤醒conditionA监视的线程
            conditionA.signal();
        }catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}
public class C {

    public static void main(String[] args) {
        Data3 data = new Data3();

        new Thread(()-> {
            for (int i = 0; i < 10; i++) {
                data.printA();
            }},"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printB();
            }},"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printC();
            }},"C").start();
    }

}
