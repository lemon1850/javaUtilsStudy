package cn.catgod.netty.rpc.client;

import cn.catgod.netty.rpc.model.RpcSerializeProtocol;

import java.lang.reflect.Proxy;

/**
 * @Author: weiqiang.lin
 * @Date: 2019-04-11
 * @Version 1.0
 */

public class MessageSendExecutor {

    private final RpcServerLoader loader = RpcServerLoader.getInstance();

    public MessageSendExecutor(String serverAddress) {
        loader.load(serverAddress, RpcSerializeProtocol.JDKSERIALIZE);
    }

    public static <T> T execute(Class<T> rpcInterface) {
        return (T) Proxy.newProxyInstance(
                rpcInterface.getClassLoader(),
                new Class<?>[]{rpcInterface},
                new MessageSendProxy<T>(rpcInterface)
        );
    }

    public void stop() {
        loader.unLoad();
    }
}
