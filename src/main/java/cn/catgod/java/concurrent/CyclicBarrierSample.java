package cn.catgod.java.concurrent;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * 对于CyclicBarrier，下一步动作实施者是“其他线程
 *
 * @Author: weiqiang.lin
 * @Date: 2019-05-15
 * @Version 1.0
 */
public class CyclicBarrierSample {

    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(3);
        for (int i = 0; i < 3; i++) {
            new Thread(new Test(barrier), "队友" + i).start();
        }

    }

    private static class Test implements Runnable {

        private final CyclicBarrier barrier;

        public Test(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {

            for (int i = 0; i < 4; i++) {

                try {

                    Random random = new Random();
                    int randomNum = random.nextInt((3000 - 1000) + 1) + 1000;//产生1000到3000之间的随机整数
                    Thread.sleep(randomNum);
                    System.out.println("第" + i + "轮 ThreadName:" + Thread.currentThread().getName());
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }
    }
}
