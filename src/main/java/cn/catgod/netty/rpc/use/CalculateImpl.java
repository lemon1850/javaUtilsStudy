package cn.catgod.netty.rpc.use;

/**
 * @Author: weiqiang.lin
 * @Date: 2019-04-11
 * @Version 1.0
 */
public class CalculateImpl implements Calculate {
    //两数相加
    @Override
    public int add(int a, int b) {

        try {

            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("被中断了");
        }
        return a + b;
    }
}
