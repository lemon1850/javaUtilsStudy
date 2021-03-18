package cn.catgod.java.concurrent;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: weiqiang.lin
 * @Date: 2019-04-25
 * @Version 1.0
 */

/**
 * 如果任务出现错误，不处理遗产个，后续不会再调度
 */
public class ScheduleJobTest {

    static int i;

    public static void main(String[] args) {

        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(5);
        service.scheduleAtFixedRate(new Job(), 3, 2, TimeUnit.SECONDS);


    }

    static class Job implements Runnable {
        @Override
        public void run() {
            try {

                System.out.println(i++);
                if (i == 5) {
                    throw new RuntimeException();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
