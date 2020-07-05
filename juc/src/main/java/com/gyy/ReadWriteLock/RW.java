package com.gyy.ReadWriteLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{
    private volatile Map<String,String> map = new HashMap<>();

    //读写锁，可以保证一写多读
    private ReadWriteLock rw = new ReentrantReadWriteLock();

    //读取
    public String get(String key){
        //读锁加锁
        rw.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"读取");
            String s = map.get(key);
            System.out.println(Thread.currentThread().getName()+"读取OK");
            return s;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //读锁释放
            rw.readLock().unlock();
        }
        return null;
    }

    //写入
    public void put(String key,String value){
        //写锁加锁
        rw.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"写入");
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写入OK");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //读锁释放
            rw.writeLock().unlock();
        }

    }
}
public class RW {

    //为了提高效率：需要使用读写锁保证数据的安全和高效读写
    /**
     * 需求：读的时候可以多个线程一起读，写的时候只允许有一个线程进行写入
     * 独占锁（写锁）
     * 共享锁（读锁）
     * 读-读：可以共存
     * 读-写：不可共存，读的时候不能写，写的时候不能读
     * 写-写：不可共存，只允许一个线程在写入
     */

    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        //测试(10个写线程)
        for (int i = 0; i < 10; i++) {
            final int t = i;
            new Thread(()->{
                myCache.put(String.valueOf(t),"OK");
            },String.valueOf(i)).start();
        }

        //测试(10个读线程)
        for (int i = 0; i < 10; i++) {
            final int t = i;
            new Thread(()->{
                myCache.get(String.valueOf(t));
            },String.valueOf(i)).start();
        }
    }

}
