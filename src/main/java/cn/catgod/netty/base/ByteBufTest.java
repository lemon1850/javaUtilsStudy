package cn.catgod.netty.base;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.StandardCharsets;

/**
 * @Author: weiqiang.lin
 * @Date: 2019-04-12
 * @Version 1.0
 */
public class ByteBufTest {

    public static void main(String[] args) {
        CompositeByteBuf byteBufs = Unpooled.compositeBuffer();
        ByteBuf buf = Unpooled.copiedBuffer("dfdfdfd", StandardCharsets.UTF_8);


    }
}
