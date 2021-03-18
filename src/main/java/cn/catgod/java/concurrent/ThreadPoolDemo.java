package cn.catgod.java.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author: weiqiang.lin
 * @Date: 2019-05-15
 * @Version 1.0
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(1);

        Future future = service.submit(new Runnable() {
            @Override
            public void run() {

            }
        });
//        future.get();
//        new FutureTask().get()
    }
}
