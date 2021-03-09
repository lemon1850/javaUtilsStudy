package cn.catgod.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;

/**
 * @author weiqiang.lin
 * @version 1.0
 * @date 2021/3/9
 */
public class LongEventMain {

    public static void main(String[] args) throws InterruptedException {

        int buffSize = 1024;
        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, buffSize, DaemonThreadFactory.INSTANCE);

        disruptor.handleEventsWith(((event, sequence, endOfBatch) -> System.out.println(event)));

        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        ByteBuffer bb = ByteBuffer.allocate(8);
        for (int i = 0; true; i++) {
            bb.putLong(0, i);
            ringBuffer.publishEvent((event, sequence, buffer) -> event.setValue(buffer.getLong(0)), bb);
            Thread.sleep(1000);
        }
    }
}
