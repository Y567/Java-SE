package com.gyy.threadPool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池
 */
public class DIYThreadPool {
    public static void main(String[] args) {
        ThreadPoolExecutor e = new ThreadPoolExecutor(
                2,
                5,
                3L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
//                new ThreadPoolExecutor.AbortPolicy());
//                new ThreadPoolExecutor.CallerRunsPolicy());
//                new ThreadPoolExecutor.DiscardOldestPolicy());
                new ThreadPoolExecutor.DiscardPolicy());

        //承载量等于maximumPoolSize+阻塞队列大小

        /**
         * 四种拒绝策略：
         * 1.AbortPolicy ：当所有线程都在运行，而且阻塞队列满了，会抛出异常
         * 2.CallerRunsPolicy ：当所有线程都在运行，而且阻塞队列满了，哪里来的回哪去
         * 3.DiscardOldestPolicy ：当所有线程都在运行，而且阻塞队列满了，尝试和最早的线程进行争夺资源，因为最早的是最可能完成任务的，抢不到就放弃任务了，不抛异常
         * 4.DiscardPolicy ：当所有线程都在运行，而且阻塞队列满了，会将新进来的任务丢弃掉
         */

        for (int i = 0; i < 9; i++) {
            e.execute(()->{
                System.out.println(Thread.currentThread().getName()+"  ok");
            });
        }

        e.shutdown();
    }
}
