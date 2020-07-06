package com.gyy.zixuan;


import java.util.concurrent.atomic.AtomicReference;

public class MyLock {

    //线程进去后就改变返回的值，导致其他线程一直在自旋,默认里面没有东西为null
    private AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock(){
        //将null改为调用该方法的线程，表示当前线程已获得锁，其他线程进来就得自旋
        while(!atomicReference.compareAndSet(null,Thread.currentThread())){
            //
        }
    }

    public void unlock(){
//        将当前线程设置回去null,这就是相当于给其他线程进来的机会了
        atomicReference.compareAndSet(Thread.currentThread(),null);
    }
}
