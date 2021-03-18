package cn.catgod.netty.rpc.client;

/**
 * @Author: weiqiang.lin
 * @Date: 2019-04-11
 * @Version 1.0
 */

import cn.catgod.netty.rpc.model.MessageRequest;
import cn.catgod.netty.rpc.model.MessageResponse;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MessageCallBack {

    private final MessageRequest request;
    private final Lock lock = new ReentrantLock();
    private final Condition finish = lock.newCondition();
    private MessageResponse response;

    public MessageCallBack(MessageRequest request) {
        this.request = request;
    }

    public Object start() throws InterruptedException {
        try {
            lock.lock();
            //设定一下超时时间，rpc服务器太久没有相应的话，就默认返回空吧。
            finish.await(10 * 1000, TimeUnit.MILLISECONDS);
            if (this.response != null) {
                return this.response.getResult();
            } else {
                return null;
            }
        } finally {
            lock.unlock();
        }
    }

    public void over(MessageResponse reponse) {
        try {
            lock.lock();
            finish.signal();
            this.response = reponse;
        } finally {
            lock.unlock();
        }
    }
}
