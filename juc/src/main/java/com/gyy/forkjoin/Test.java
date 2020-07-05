package com.gyy.forkjoin;

import java.awt.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class Test {

    //普通方式
    public void test1(){
        long start = System.currentTimeMillis();
        long sum = 0L;
        for (long i = 1L; i <= 10_0000_0000L; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("耗费了"+(end-start)+" "+"sum=>"+sum);
    }

    //forkJoin方式
    public void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinDemo task = new ForkJoinDemo(1L, 10_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);//异步提交任务去执行
        Long sum = submit.get();//获得结果
        long end = System.currentTimeMillis();
        System.out.println("耗费了"+(end-start)+" "+"sum=>"+sum);
    }

    //stream并行计算
    public void test3(){
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(1L, 10_0000_0000L).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("耗费了"+(end-start)+" "+"sum=>"+sum);
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Test test = new Test();
        test.test1();
//        test.test2();
        test.test3();
    }
}
