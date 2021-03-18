package cn.catgod.netty.rpc.server;

/**
 * @Author: weiqiang.lin
 * @Date: 2019-04-11
 * @Version 1.0
 */

import cn.catgod.netty.rpc.model.MessageRequest;
import cn.catgod.netty.rpc.model.MessageResponse;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.beanutils.MethodUtils;

import java.util.Map;

public class MessageRecvInitializeTask implements Runnable {

    private MessageRequest request = null;
    private MessageResponse response = null;
    private Map<String, Object> handlerMap = null;
    private ChannelHandlerContext ctx = null;

    MessageRecvInitializeTask(MessageRequest request, MessageResponse response, Map<String, Object> handlerMap, ChannelHandlerContext ctx) {
        this.request = request;
        this.response = response;
        this.handlerMap = handlerMap;
        this.ctx = ctx;
    }

    public MessageResponse getResponse() {
        return response;
    }

    public MessageRequest getRequest() {
        return request;
    }

    public void setRequest(MessageRequest request) {
        this.request = request;
    }

    @Override
    public void run() {
        response.setMessageId(request.getMessageId());
        try {
            Object result = reflect(request);
            response.setResult(result);
        } catch (Throwable t) {
            response.setError(t.toString());
            t.printStackTrace();
            System.err.printf("RPC Server invoke error!\n");
        }

        ctx.writeAndFlush(response).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                System.out.println("RPC Server Send message-id respone:" + request.getMessageId());
            }
        });
    }

    private Object reflect(MessageRequest request) throws Throwable {
        String className = request.getClassName();
        Object serviceBean = handlerMap.get(className);
        String methodName = request.getMethodName();
        Object[] parameters = request.getParameters();
        return MethodUtils.invokeMethod(serviceBean, methodName, parameters);
    }
}
