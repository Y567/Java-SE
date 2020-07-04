package com.gyy.callable;

import javafx.concurrent.Task;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Call {

    static class Task implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println("执行了");
            return 0;
        }
    }
    public static void main(String[] args) {
        //FutureTask实现了Runnable接口
        FutureTask<Integer> task = new FutureTask<>(new Task());
        new Thread(task).start();
    }
}
