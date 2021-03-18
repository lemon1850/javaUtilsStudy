package cn.catgod.netty.rpc;

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
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(new Date() + ": 服务器读到数据 -> " + byteBuf.toString(Charset.defaultCharset()));

        System.out.println(new Date() + ": 服务端写出数据");
        ByteBuf writeByteBuf = getByteBuf(ctx, "hello xiaomaomi: ");
        ctx.channel().writeAndFlush(writeByteBuf);

    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx, String output) {
        ByteBuf buffer = ctx.alloc().buffer();

        byte[] bytes = output.getBytes(StandardCharsets.UTF_8);
        buffer.writeBytes(bytes);
        return buffer;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        ByteBuf byteBuf = getByteBuf(ctx, "连接成功\n");
        ctx.channel().writeAndFlush(byteBuf);


    }
}
