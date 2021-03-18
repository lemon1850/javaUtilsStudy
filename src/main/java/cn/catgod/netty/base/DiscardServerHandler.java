package cn.catgod.netty.base;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ((ByteBuf)msg).release();
//        super.channelRead(ctx, msg);
//        ByteBuf buf = (ByteBuf)msg;
//        try{
//            while (buf.isReadable()){
//                System.out.println((char)buf.readByte());
//                System.out.flush();
//            }
//        }finally {
//            ReferenceCountUtil.release(msg);
//        }
        ctx.write(msg);
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }
}
