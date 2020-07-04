package com.gyy.unsafe;

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 处理线程不安全的问题的解决
 */
public class Test1 {

    public static void main(String[] args) {
//        List<String> lists = Collections.synchronizedList(new ArrayList<>());
        List<String> lists = new CopyOnWriteArrayList<>();
        /**
         * 解决线程不安全的问题
         * 1.使用Vector
         * 2.使用Collections.synchronizedList等方法
         * 3.使用CopyOnWriteArrayList，并发包下的读写，写入时复制：在写入时，先复制原有的数据，然后再插入新的值，最后将整个数据再设置回去
         */
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                lists.add(UUID.randomUUID().toString().substring(0,4));
                System.out.println(lists);
            }).start();

            new CopyOnWriteArraySet<>();
        }

    }
}
