package cn.catgod.netty.base;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: weiqiang.lin
 * @Date: 2019-04-12
 * @Version 1.0
 */
public class PlainNioServer {


    public void serve(int port) throws IOException {

        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        ServerSocket serverSocket = serverChannel.socket();
        InetSocketAddress address = new InetSocketAddress(port);
        serverSocket.bind(address);

        Selector selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        final ByteBuffer msg = ByteBuffer.wrap("hi! miaomi\\r\\n".getBytes());
        for (; ; ) {
            try {
                selector.select();
            } catch (IOException e) {
                e.printStackTrace();

                break;
            }

            Set<SelectionKey> readyKeys = selector.keys();
            Iterator<SelectionKey> iterator = readyKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();

                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel client = server.accept();
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, msg.duplicate());

                }
                if (key.isWritable()) {
                    SocketChannel client = (SocketChannel) key.channel();
                    client.write(msg);
                    client.close();
                }
            }


        }
    }
}
