package com.gyy.blockQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列的四组API
 */
public class BQ {

    /**
     * 第一组抛出异常的
     */
    public static void test1(){
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.add(1);
        blockingQueue.add(2);
        blockingQueue.add(3);
//        blockingQueue.add(4);
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
    }

    /**
     * 第二组不会抛出异常但是会返回true或false
     */
    public static void test2() throws InterruptedException {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.offer(1);
        blockingQueue.offer(2);
        blockingQueue.offer(3);
        System.out.println(blockingQueue.offer(4));
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
    }

    /**
     * 第三组一直阻塞等待
     */
    public static void test3() throws InterruptedException {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.put(1);
        blockingQueue.put(2);
        blockingQueue.put(3);
        blockingQueue.put(4);
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());
    }

    /**
     * 第三组超市等待
     */
    public static void test4() throws InterruptedException {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.offer(1,2, TimeUnit.SECONDS);
        blockingQueue.offer(2,2,TimeUnit.SECONDS);
        blockingQueue.offer(3,2, TimeUnit.SECONDS);
//        blockingQueue.offer(4,2, TimeUnit.SECONDS);
        System.out.println(blockingQueue.poll(2,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2,TimeUnit.SECONDS));
    }

    public static void main(String[] args) throws InterruptedException {
        //添加和删除会抛出异常的
//        test1();
        //有返回值不抛异常
//        test2();
        //会一直阻塞等待下去
//        test3();
        //超时等待
        test4();
    }
}
