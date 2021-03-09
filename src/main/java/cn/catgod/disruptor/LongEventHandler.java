package cn.catgod.disruptor;


import com.lmax.disruptor.EventHandler;

/**
 * @author weiqiang.lin
 * @version 1.0
 * @date 2021/3/9
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println(event);
    }
}
