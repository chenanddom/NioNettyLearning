package com.itdom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * public final boolean isAcceptable（）方法的作用是测
 * 试此键的通道是否已准备好接受新的套接字连接。调用
 * 此方法的形式为k.isAcceptable（） ，该调用与以下调用的
 * 作用完全相同： k.readyOps() & OP ACCEPT != 0 。如果
 * 此键的通道不支持套接字连接操作，则此方法始终返回
 * false 。返回值当且仅当readyOps() & OP _ACCEPT 为非
 * 零值时才返回true o
 * public final boolean isConnectable （） 方法的作用是测
 * 试此键的通道是否已完成其套接字连接操作。调用此方
 * 法的形式为k.isConnectable（），该调用与以下调用的作
 * 用完全相同： k.readyOps() & OP CONNECT!= O 。如果
 * 此键的通道不支持套接字连接操作， 则此方法始终返回
 * false 。返回值当且仅当readyOps() & OP_ CONNECT 为非零值时才返回true 。
 * public abstract SelectableChannel channel（）方法的作用是返回为之创建此键的通道。即
 * 使已取消该键，此方法仍继续返回通道。
 */
public class SelectableChannelTest {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost",8888));
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            selector.select();
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeySet.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                Socket socket = null;
                if (key.isAcceptable()){
                    socket = channel.socket().accept();
                    System.out.println("server isAcceptable()");
                }
                socket.close();
                iterator.remove();
            }
        }
        /**
         * 执行SelectableChannelTest然后执行SelectableChannelTest2
         * 执行结果：server isAcceptable()
         */
    }
}
