package cn.catgod.netty.base;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        final ByteBuf bytebuf = ctx.alloc().buffer(4);
        bytebuf.writeInt((int) (System.currentTimeMillis() / 100L + 2208988800L));
        ChannelFuture future = ctx.writeAndFlush(bytebuf);
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                ctx.close();
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
//        ctx.channel().writeAndFlush("fdf").addListener()
        ctx.close();
    }
}
