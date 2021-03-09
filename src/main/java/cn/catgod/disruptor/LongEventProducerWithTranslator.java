package cn.catgod.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.nio.ByteBuffer;

/**
 * @author weiqiang.lin
 * @version 1.0
 * @date 2021/3/9
 */
@AllArgsConstructor
public class LongEventProducerWithTranslator {

    private final RingBuffer<LongEvent> ringBuffer;

    private static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSLATOR =
            new EventTranslatorOneArg<LongEvent, ByteBuffer>() {
                @Override
                public void translateTo(LongEvent event, long sequence, ByteBuffer arg0) {
                    event.setValue(arg0.getLong(0));
                }
            };

    public void onData(ByteBuffer bb){
        ringBuffer.publishEvent(TRANSLATOR, bb);
    }
}
