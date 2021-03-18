package cn.catgod.netty.rpc.client;

/**
 * @Author: weiqiang.lin
 * @Date: 2019-04-11
 * @Version 1.0
 */

import cn.catgod.netty.rpc.RpcThreadPool;
import cn.catgod.netty.rpc.model.RpcSerializeProtocol;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

import java.net.InetSocketAddress;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class RpcServerLoader {

    private final static String DELIMITER = ":";
    //方法返回到Java虚拟机的可用的处理器数量
    private final static int parallel = Runtime.getRuntime().availableProcessors() * 2;
    private static final ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) RpcThreadPool.getExecutor(16, -1);
    private volatile static RpcServerLoader rpcServerLoader;
    //netty nio线程池
    private final EventLoopGroup eventLoopGroup = new NioEventLoopGroup(parallel);
    //等待Netty服务端链路建立通知信号
    private final Lock lock = new ReentrantLock();
    private final Condition signal = lock.newCondition();
    private RpcSerializeProtocol serializeProtocol = RpcSerializeProtocol.JDKSERIALIZE;
    private MessageSendHandler messageSendHandler = null;

    private RpcServerLoader() {
    }

    //并发双重锁定
    public static RpcServerLoader getInstance() {
        if (rpcServerLoader == null) {
            synchronized (RpcServerLoader.class) {
                if (rpcServerLoader == null) {
                    rpcServerLoader = new RpcServerLoader();
                }
            }
        }
        return rpcServerLoader;
    }

    public void load(String serverAddress, RpcSerializeProtocol serializeProtocol) {
        String[] ipAddr = serverAddress.split(RpcServerLoader.DELIMITER);
        if (ipAddr.length == 2) {
            String host = ipAddr[0];
            int port = Integer.parseInt(ipAddr[1]);
            final InetSocketAddress remoteAddr = new InetSocketAddress(host, port);

            threadPoolExecutor.submit(new MessageSendInitializeTask(eventLoopGroup, remoteAddr, this));
        }
    }

    public MessageSendHandler getMessageSendHandler() throws InterruptedException {
        try {
            lock.lock();
            //Netty服务端链路没有建立完毕之前，先挂起等待
            if (messageSendHandler == null) {
                signal.await();
            }
            return messageSendHandler;
        } finally {
            lock.unlock();
        }
    }

    public void setMessageSendHandler(MessageSendHandler messageInHandler) {
        try {
            lock.lock();
            this.messageSendHandler = messageInHandler;
            //唤醒所有等待客户端RPC线程
            signal.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void unLoad() {
        messageSendHandler.close();
        threadPoolExecutor.shutdown();
        eventLoopGroup.shutdownGracefully();
    }

    public void setSerializeProtocol(RpcSerializeProtocol serializeProtocol) {
        this.serializeProtocol = serializeProtocol;
    }
}
