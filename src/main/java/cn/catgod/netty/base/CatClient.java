package cn.catgod.netty.base;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.TimeUnit;

/**
 * @author weiqiang.lin
 * @version 1.0
 * @date 2019/12/30
 */
public class CatClient {

    public static void main(String[] args) {

        NioEventLoopGroup works = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .group(works)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {

                        ch.pipeline().addLast(
                                new FirstClientHandler()
//                                new ChannelOutboundHandlerAdapter(){
//                            @Override
//                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
//                                super.write(ctx, msg, promise);
//                                System.out.println(msg);
//                                System.out.println("write");
//                            }
//
//                            @Override
//                            public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) throws Exception {
//                                super.bind(ctx, localAddress, promise);
//                                System.out.println("bind");
//
//                            }
//
//                            @Override
//                            public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
//                                super.connect(ctx, remoteAddress, localAddress, promise);
//                                System.out.println("connect");
//                            }
//
//                            @Override
//                            public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
//                                super.disconnect(ctx, promise);
//                                System.out.println("disconnect");
//
//                            }
//
//                            @Override
//                            public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
//                                super.close(ctx, promise);
//                                System.out.println("close");
//
//                            }
//
//                            @Override
//                            public void deregister(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
//                                super.deregister(ctx, promise);
//                                System.out.println("deregister");
//                            }
//
//                            @Override
//                            public void read(ChannelHandlerContext ctx) throws Exception {
//                                super.read(ctx);
//                                System.out.println("read");
//                            }
//
//                            @Override
//                            public void flush(ChannelHandlerContext ctx) throws Exception {
//                                super.flush(ctx);
//                                System.out.println("flush");
//                            }
//                        }
                        );
                    }
                });
        connect(bootstrap, "127.0.0.1", 6666, 3);
    }

    public static void connect(Bootstrap bootstrap, String ip, int port, int retry) {
        bootstrap.connect(ip, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("链接成功");
            } else if (retry == 0) {
                System.out.println("重试次数用完");
            } else {
                int order = (3 - retry) + 1;
                int delay = 1 << order;
                System.err.println(String.format("第%s次重试", order));
                bootstrap.config().group().schedule(() -> connect(bootstrap, ip, port, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
    }
}
