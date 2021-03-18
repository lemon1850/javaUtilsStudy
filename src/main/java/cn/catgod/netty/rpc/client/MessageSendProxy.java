package cn.catgod.netty.rpc.client;

/**
 * @Author: weiqiang.lin
 * @Date: 2019-04-11
 * @Version 1.0
 */


import cn.catgod.netty.rpc.model.MessageRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;

public class MessageSendProxy<T> implements InvocationHandler {

    private final Class<T> cls;

    public MessageSendProxy(Class<T> cls) {
        this.cls = cls;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MessageRequest request = new MessageRequest();
        request.setMessageId(UUID.randomUUID().toString());
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setTypeParameters(method.getParameterTypes());
        request.setParameters(args);

        MessageSendHandler handler = RpcServerLoader.getInstance().getMessageSendHandler();
        MessageCallBack callBack = handler.sendRequest(request);
        return callBack.start();
    }
}
