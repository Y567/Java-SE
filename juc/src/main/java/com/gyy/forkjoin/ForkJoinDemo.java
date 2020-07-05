package com.gyy.forkjoin;

import java.util.concurrent.RecursiveTask;

//继承一个RecursiveTask类
public class ForkJoinDemo extends RecursiveTask<Long> {

    private long start;
    private long end;
    //临界值,改变这个可以改变效率
    private long temp = 1000L;

    public ForkJoinDemo(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if((end - start) < temp){
            //普通计算即可
            long sum = 0L;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }else{
            //使用forkJoin计算
            Long middle = (start + end)>>1;
            ForkJoinDemo left = new ForkJoinDemo(start, middle);
            left.fork();//加入线程队列，准备计算
            ForkJoinDemo right = new ForkJoinDemo(middle + 1, end);
            right.fork();
            return left.join() + right.join();
        }
    }
}
