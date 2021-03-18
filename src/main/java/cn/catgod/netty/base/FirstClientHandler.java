package cn.catgod.netty.base;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author weiqiang.lin
 * @version 1.0
 * @date 2019/12/30
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println(new Date() + ": 客户端写出数据");

        ByteBuf buffer = getByteBuf(ctx);

        ctx.channel().writeAndFlush(buffer);
//        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println(new Date() + ": 客户端读到数据");

        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(byteBuf.toString(Charset.defaultCharset()));
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        ByteBuf buffer = ctx.alloc().buffer();

        byte[] bytes = "hello server".getBytes(StandardCharsets.UTF_8);
        buffer.writeBytes(bytes);
        return buffer;
    }
}
