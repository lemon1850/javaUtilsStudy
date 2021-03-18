package cn.catgod.java.concurrent;

/**
 * @Author: weiqiang.lin
 * @Date: 2019-05-15
 * @Version 1.0
 */
public class ThreadDemo {


    public static void main(String[] args) {

        new ThreadX().start();
//        Thread.setDefaultUncaughtExceptionHandler();
    }

    private static class ThreadX extends Thread {
        @Override
        public void run() {
            System.out.println("go");
        }
    }

//    Thread.UncaughtExceptionHandler
}
