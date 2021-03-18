package cn.catgod.redission;

import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @author weiqiang.lin
 * @version 1.0
 * @date 2021/3/9
 */
public class RedissonDemo {

    public static void main(String[] args) {
        Config config = new Config();

//        config.useClusterServers()
//                .setScanInterval(2000) // 集群状态扫描间隔时间，单位是毫秒
//                //可以用"rediss://"来启用SSL连接
//                .addNodeAddress("redis://127.0.0.1:6379");

        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
//        RedissonClient redisson = Redisson.create(config);
//config.setPassword("password")//设置密码
//        config.useSingleServer().setConnectionPoolSize(500);//设置对于master节点的连接池中连接数最大为500
//        config.useSingleServer().setIdleConnectionTimeout(10000);//如果当前连接池里的连接数量超过了最小空闲连接数，而同时有连接空闲时间超过了该数值，那么这些连接将会自动被关闭，并从连接池里去掉。时间单位是毫秒。
//        config.useSingleServer().setConnectTimeout(30000);//同任何节点建立连接时的等待超时。时间单位是毫秒。
//        config.useSingleServer().setTimeout(3000);//等待节点回复命令的时间。该时间从命令发送成功时开始计时。
////        config.useSingleServer().setPingTimeout(30000)
//        config.useSingleServer().setReconnectionTimeout(3000)//当与某个节点的连接断开时，等待与其重新建立连接的时间间隔。时间单位是毫秒。

//创建客户端(发现创建RedissonC
// lient非常耗时，基本在2秒-4秒左右)
        RedissonClient redisson = Redisson.create(config);
        RAtomicLong atomicLong = redisson.getAtomicLong("test");
        atomicLong.set(100);
        atomicLong.getAndAdd(20);
        System.out.println(atomicLong.get());
        RLock lock = redisson.getLock("fdf");
        lock.lock();

        lock.unlock();


    }
}
