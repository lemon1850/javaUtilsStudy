package cn.catgod.netty.rpc.use;

import cn.catgod.netty.rpc.server.MessageRecvExecutor;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: weiqiang.lin
 * @Date: 2019-04-12
 * @Version 1.0
 */
public class RunServer {

    public static void main(String[] args) throws Exception {

        MessageRecvExecutor messageRecvExecutor = new MessageRecvExecutor("127.0.0.1:18888");
        Map<String, Object> map = new HashMap<>();
        map.put("cn.catgod.netty.rpc.use.Calculate", new CalculateImpl());
        messageRecvExecutor.setHandlerMap(map);
        messageRecvExecutor.afterPropertiesSet();

    }
}
