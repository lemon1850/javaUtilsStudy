package cn.catgod.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author weiqiang.lin
 * @version 1.0
 * @date 2021/3/9
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
