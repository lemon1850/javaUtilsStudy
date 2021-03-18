package cn.catgod.netty.base;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author weiqiang.lin
 * @version 1.0
 * @date 2019/12/30
 */
public class CatNettyServer {

    public static void main(String[] args) {

        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup client = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.channel(NioServerSocketChannel.class)
                .group(boss, client)
                .option(ChannelOption.SO_BACKLOG, 1)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .attr(AttributeKey.newInstance("name"), "cat")
                .childAttr(AttributeKey.valueOf("childName"), "littleCat")
                .handler(new ChannelInitializer<NioServerSocketChannel>() {

                    @Override
                    protected void initChannel(NioServerSocketChannel nioServerSocketChannel) throws Exception {
                        System.out.println("服务启动中");
                    }
                })
                .childHandler(new ChannelInitializer<NioSocketChannel>() {

                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(
                                new FirstServerHandler()
//                                new ChannelInboundHandlerAdapter(){
//                            @Override
//                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//                                System.out.println(msg);
//                                super.channelRead(ctx, msg);
//                                System.out.println("channelRead");
//                            }
//
//                            @Override
//                            public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//                                super.channelReadComplete(ctx);
//                                System.out.println("finish");
//                                System.out.println("channelReadComplete");
//
//                            }
//
//                            @Override
//                            public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
//                                super.channelRegistered(ctx);
//                                System.out.println("channelRegistered");
//                            }
//
//                            @Override
//                            public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
//                                super.channelUnregistered(ctx);
//                                System.out.println("channelUnregistered");
//                            }
//
//                            @Override
//                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
//                                super.channelActive(ctx);
//                                System.out.println("channelActive");
//                            }
//
//                            @Override
//                            public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//                                super.channelInactive(ctx);
//                                System.out.println("channelInactive");
//                            }
//
//                            @Override
//                            public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
//                                super.userEventTriggered(ctx, evt);
//                                System.out.println("userEventTriggered");
//                            }
//
//
//                            @Override
//                            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//                                super.exceptionCaught(ctx, cause);
//                                System.out.println("exceptionCaught");
//                            }
//                        }
                        );
                    }
                });
        ChannelFuture channelFuture = serverBootstrap.bind("0.0.0.0", Integer.parseInt("6666"));
        channelFuture.addListener(new GenericFutureListener<Future<? super Void>>() {
                                      @Override
                                      public void operationComplete(Future<? super Void> future) throws Exception {
                                          System.out.println("bind sucess");
                                      }
                                  }

        );
    }
}
