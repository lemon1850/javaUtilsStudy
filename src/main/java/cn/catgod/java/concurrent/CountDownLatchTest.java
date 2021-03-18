package cn.catgod.java.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch，当计数为0的时候，下一步的动作实施者是main函数
 *
 * @Author: weiqiang.lin
 * @Date: 2019-05-15
 * @Version 1.0
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws Exception {
        CountDownLatch latch = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            new Thread(new Test(latch)).start();
        }

        System.out.println("玩家准备");
        latch.await();

        System.out.println("游戏开始");
    }

    private static class Test implements Runnable {

        private final CountDownLatch latch;

        public Test(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {

            try {

                Random random = new Random();
                int randomNum = random.nextInt((3000 - 1000) + 1) + 1000;//产生1000到3000之间的随机整数
                Thread.sleep(randomNum);
                latch.countDown();
                System.out.println("Thread" + Thread.currentThread().getName() + "准备了" + random + "毫秒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
